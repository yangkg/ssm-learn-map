package com.weixin.pojo;

;
;import java.util.List;

/**
 * 图文消息
 *
 * @author：Kyle.yangkg
 * @create：2017-08-11 下午 2:31
 * ©copyright：www.aisino.com
 */
public class NewsMessage extends BaseMessage {
    private int ArticleCount;
    private List<News> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
    }
}

