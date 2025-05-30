package org.ansj.elasticsearch.action;

import org.elasticsearch.action.Action;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * Created by zhangqinghua on 16/2/2.
 */
public class AnsjAction extends Action<AnsjRequest, AnsjResponse, AnsjRequestBuilder> {

    static final String NAME = "cluster:admin/ansj/analyze";

    public static final AnsjAction INSTANCE = new AnsjAction(NAME);

    public AnsjAction(String name) {
        super(name);
    }

    @Override
    public AnsjResponse newResponse() {
        return new AnsjResponse();
    }

    @Override
    public AnsjRequestBuilder newRequestBuilder(ElasticsearchClient client) {
        return new AnsjRequestBuilder(client, this);
    }
}
