#!/usr/bin/python
# -*- coding: utf-8 -*-  

import nltk
import argparse 
import jieba
import pickle
import sys
import os
from nltk.collocations import BigramCollocationFinder
from nltk.metrics import BigramAssocMeasures
from nltk.probability import FreqDist, ConditionalFreqDist
from nltk import NaiveBayesClassifier, classify

dir = os.path.dirname(os.path.realpath(__file__))

stoplist = {}
with open(os.path.join(dir, 'stopWord.dic')) as handle:
    for line in handle.readlines():
        stoplist[line.strip().decode('utf8')] = True

def get_features(corpus):
    words = []
    for token in jieba.cut(corpus):
        if stoplist.has_key(token):
            continue
        words.append(token)
    
    bigram_finder = BigramCollocationFinder.from_words(words)
    bigram_words = []
    try:
        bigram_words = bigram_finder.nbest(BigramAssocMeasures.chi_sq, 1000)
    except:
        pass
    
    return words + bigram_words

def create_word_bigram_scores(pos_corpus, neg_corpus):   
    
    word_fd = FreqDist()
    cond_word_fd = ConditionalFreqDist()
    for corpus in pos_corpus:
        for word in corpus:
            word_fd[word] += 1
            cond_word_fd['pos'][word] += 1
    for corpus in neg_corpus:
        for word in corpus:
            word_fd[word] += 1
            cond_word_fd['neg'][word] += 1

    pos_word_count = cond_word_fd['pos'].N()
    neg_word_count = cond_word_fd['neg'].N()
    total_word_count = pos_word_count + neg_word_count

    word_scores = {}
    for word, freq in word_fd.iteritems():
        pos_score = BigramAssocMeasures.chi_sq(cond_word_fd['pos'][word], (freq, pos_word_count), total_word_count)
        neg_score = BigramAssocMeasures.chi_sq(cond_word_fd['neg'][word], (freq, neg_word_count), total_word_count)
        word_scores[word] = pos_score + neg_score

    return word_scores
    
def find_best_words(word_scores, number):    
    best_vals = sorted(word_scores.iteritems(), key=lambda (w, s): s, reverse=True)[:number] #把词按信息量倒序排序。number是特征的维度，是可以不断调整直至最优的
    best_words = set([w for w, s in best_vals])
    return best_words

def best_word_features(words, best_words):
    return dict([(word, True) for word in words if word in best_words])
    
def train():
    def readSimple(filename):
        simple = []
        with open(filename, 'r') as handle:
            for line in handle.readlines():
                simple.append(line.strip())
        return simple
    
    pos_corpus = []
    for corpus in readSimple('raw_pos.txt'):
        pos_corpus.append(get_features(corpus))
    neg_corpus = []
    for corpus in readSimple('raw_neg.txt'):
        neg_corpus.append(get_features(corpus))
    
    words_scores = create_word_bigram_scores(pos_corpus, neg_corpus)
    best_words   = find_best_words(words_scores, 1500)
    
    pos_features = [(best_word_features(words, best_words), 'pos') for words in pos_corpus]
    neg_features = [(best_word_features(words, best_words), 'neg') for words in neg_corpus]
    
    pos_size = int(len(pos_features) * 0.8)
    neg_size = int(len(neg_features) * 0.8)
    # train the classifier
    train_set = pos_features[:pos_size] + neg_features[:neg_size]
    test_set  = pos_features[pos_size:] + neg_features[neg_size:]
    classifier = NaiveBayesClassifier.train(train_set)
    
    print ('Accuracy on the training set = ' + str(classify.accuracy(classifier, train_set)))
    print ('Accuracy of the test set = ' + str(classify.accuracy(classifier, test_set)))
    # check which words are most informative for the classifier
    classifier.show_most_informative_features(20)
      
    with open('NaiveBayesClassifier.pkl', 'wb') as handle:
        pickle.dump(classifier, handle)
    
    with open('best_words', 'wb') as handle:
        pickle.dump(best_words, handle)

classifier = None
best_words = []
def doclassify(text):
    global classifier, best_words
    
    if not classifier:
        with open(os.path.join(dir, 'NaiveBayesClassifier.pkl'), 'r') as handle:
            classifier = pickle.load(handle)
    
    if not best_words:
        with open(os.path.join(dir, 'best_words'), 'r') as handle:
            best_words = pickle.load(handle)
    
    if not classifier:
        raise TypeError('no model can use.')
    
    words = get_features(text)
    features = best_word_features(words, best_words)
    pdist = classifier.prob_classify(features)
    
    return (pdist.prob('pos'), pdist.prob('neg'))
    
if __name__ == '__main__':
   a  = doclassify(sys.argv[1])
   print a
    
