package org.ansj.elasticsearch.index.analysis;

import org.ansj.library.DicLibrary;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.compound.AbstractCompoundWordTokenFilterFactory;
import org.nlpcn.commons.lang.tire.domain.Forest;

public class AnsjCompoundWordTokenFilterFactory extends AbstractCompoundWordTokenFilterFactory {

    private final Forest[] forests;

    public AnsjCompoundWordTokenFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, env, name, settings);

        String[] dicList = settings.getAsArray(DicLibrary.DEFAULT, null, true);
        if (dicList == null) {
            throw new IllegalArgumentException("dic must be provided for [" + name + "]");
        }

        forests = DicLibrary.gets(dicList);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new AnsjCompoundWordTokenFilter(tokenStream, forests, minWordSize,
                minSubwordSize, maxSubwordSize, onlyLongestMatch);
    }
}
