# IntelligenceAnalyzer
主要功能：情感分析|文本分类|实体识别|语义联想|摘要提取

情感分析:主要使用python jieba包
		
	1. 安装 python-2.7.10。
	2. 安装 nltk、jieba、argparse(使用命令：pip install 包名)。
	3. 把python安装目录以及其下面的Scripts目录配置到path中。
	
文本分类： 使用  [LingPipe](http://alias-i.com/lingpipe/)

实体识别： 主要使用[jcseg-core](http://git.oschina.net/lionsoul/jcseg#only_comment)以及词性[api](http://www.hankcs.com/nlp/part-of-speech-tagging.html)

语义联想： 使用[deeplearning4j](https://deeplearning4j.org/cn/)下的Word2Vec

摘要提取： 使用[jcseg-core](http://git.oschina.net/lionsoul/jcseg#only_comment)

备注：

   1. 语义联想需要的模型(联系我bigitli@foxmail.com)，下载后放到项目\src\main\resources目录下。
     
   2. jcseg-core jar包大，可以更新maven下载也可以[点此下载](http://pan.baidu.com/s/1i5Fo6dZ),下载后放到项目\sources\lib\sf目录下。

