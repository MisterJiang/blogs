package com.core.lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class LuceneUtils {

    /**
     * 创建索引
     * @param path 文件地址
     */
    public static void addOrUpdateIndexOrDelete(String path, String type){
        try {
            if (type.equals("add")){
                System.out.println("开始创建索引......");
            }else if(type.equals("update")){
                System.out.println("开始更新索引......");
            }
            FSDirectory directory = FSDirectory.open((Paths.get(path)));
            //单分词器
          //  StandardAnalyzer analyzer = new StandardAnalyzer();
            //中文分词
        //    IKAnalyzer ikAnalyzer = new IKAnalyzer();
            Analyzer analyzer = new SmartChineseAnalyzer(new CharArraySet(Arrays.asList("的", "了", "啊"),true));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter indexWriter = new IndexWriter(directory, config);
            if (type.equals("add")){
                Document document = new Document();
                document.add(new StringField("articleId", "0002", Field.Store.YES));
                document.add(new TextField("title","lucene的得分做一个简单的介绍", Field.Store.YES));
                document.add(new TextField("content","检索的得分相当重要，这关乎你的搜索结果排名，" +
                        "因为百度搜索的东西特别多好多页，用户根本不可能会一直看到100页，也就会第一页和后几页，在访问量为王的的互联网时代，" +
                        "排名相对重要。lucene对于得分有一套自己的算法，当然也可以人工干预，比如给钱。提高得分现在比较大的就是在贴吧多发贴等等，其实最实在的就是你给百度钞票，" +
                        "给了钞票越多你的搜索结果排名越靠前，本文对于lucene的得分做一个简单的介绍", Field.Store.YES));
                long addDocument = indexWriter.addDocument(document);
                System.out.println("创建"+ addDocument +"个索引成功！");
            }else if (type.equals("update")){
                Document document = new Document();
                document.add(new StringField("articleId", "0001", Field.Store.YES));
                document.add(new TextField("title","我们新建百度IndexWriter", Field.Store.YES));
                document.add(new TextField("content","上面的代码中我们新建了一个IndexWriter，", Field.Store.YES));
                indexWriter.updateDocument(new Term("articleId", "0001"), document);
                System.out.println("更新索引成功！");
            }else if (type.equals("delete")){
               // indexWriter.deleteDocuments(new Term("articleId", "0001"));
                long deleteAll = indexWriter.deleteAll();  //删除所有索引
                System.out.println("删除索引成功！");
            }
            indexWriter.commit();
            indexWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }

    public static void searchIndex(String path, String keywords){
        try {
            System.out.println("开始查询索引......");
            FSDirectory directory = FSDirectory.open((Paths.get(path)));

            //指定读取的索引目录返回相应的实例
            IndexReader indexReader = DirectoryReader.open(directory);
            //进行全文检索
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
          //  TermQuery query = new TermQuery(new Term("title", "IndexWriter"));

            //用分词器
            Analyzer analyzer = new SmartChineseAnalyzer();
            //单字段查询
           // QueryParser queryParser = new QueryParser("content", analyzer);
            //多字段条件查询
            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(new String[]{"title", "content"}, analyzer);
             //传入查询的字段 keywords
            Query query = queryParser.parse(keywords);
            /*************************代码高亮***********************************/
            //对关键字进行处理
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            //通过Query包装Scorer
            QueryScorer fragmentScorer = new QueryScorer(query);
            Highlighter highlighter = new Highlighter(formatter, fragmentScorer);
            //创建格式化片段
            SimpleFragmenter fragmenter = new SimpleFragmenter(1000);
            //通过highlighter对文本进行处理
            highlighter.setTextFragmenter(fragmenter);
            /*************************代码高亮***********************************/
            //查询索引表
            TopDocs topDocs = indexSearcher.search(query, 10);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("获取到记录数：" + scoreDocs.length);

            for (int i = 0; i < scoreDocs.length; i++) {
                //获取记录lucene创建的唯一的 id
                int id = scoreDocs[i].doc;
                //检索得分
                float score = scoreDocs[i].score;
                System.out.println("id = " + id);
                System.out.println("检索得分 score = " + score);
                //查询数据表 通过id获取内容
                Document doc = indexSearcher.doc(id);
                String articleId = doc.get("articleId");
                String title = doc.get("title");
                String content = doc.get("content");
                System.out.println("articleId = " + articleId);
                System.out.println("title = " + title);
                System.out.println("content = " + content);
                String bestFragmentTitle = highlighter.getBestFragment(analyzer, "title", title);
                String bestFragmentContent = highlighter.getBestFragment(analyzer, "content", content);
                System.out.println("高亮 title = " + bestFragmentTitle);
                System.out.println("高亮 content = " + bestFragmentContent);

            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }catch (InvalidTokenOffsetsException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
      LuceneUtils.searchIndex("E:\\\\GIT\\\\blogs\\\\src\\\\main\\\\resources\\\\luceneIndex\\\\article_tb", "重要");



       //LuceneUtils.addOrUpdateIndexOrDelete("E:\\GIT\\blogs\\src\\main\\resources\\luceneIndex\\article_tb", "add");
    //    LuceneUtils.addOrUpdateIndexOrDelete("E:\\GIT\\blogs\\src\\main\\resources\\luceneIndex\\article_tb", "update");
       //LuceneUtils.addOrUpdateIndexOrDelete("E:\\GIT\\blogs\\src\\main\\resources\\luceneIndex\\article_tb", "delete");
    }

}
