package org.ansj.elasticsearch.action;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.single.shard.SingleShardRequest;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangqinghua on 16/2/2.
 */
public class AnsjRequest extends SingleShardRequest<AnsjRequest> {

    private String path;

    private Map<String, Object> args = new HashMap<>();

    private BytesReference source;

    public AnsjRequest() {
    }

    public AnsjRequest(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String get(String key) {
        return (String) args.get(key);
    }

    public String put(String key, String value) {
        return (String) args.put(key, value);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        path = in.readString();
        args = in.readMap();
        source = in.readBytesReference();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(path);
        out.writeMap(args);
        out.writeBytesReference(source);
    }

    public Map<String, Object> asMap() {
        return args;
    }
}
