package org.ansj.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.nlpcn.commons.lang.tire.GetWord;
import org.nlpcn.commons.lang.tire.domain.Forest;

public class AnsjCompoundWordTokenFilter extends CompoundWordTokenFilterBase {

    private final Forest[] forests;

    public AnsjCompoundWordTokenFilter(TokenStream input, Forest[] forests,
                                       int minWordSize, int minSubwordSize, int maxSubwordSize, boolean onlyLongestMatch) {
        super(input, null, minWordSize, minSubwordSize, maxSubwordSize, onlyLongestMatch);

        if (forests == null) {
            throw new IllegalArgumentException("forest must not be null");
        }

        this.forests = forests;
    }

    @Override
    protected void decompose() {
        Forest forest;
        for (int i = forests.length - 1; i >= 0; i--) {
            forest = forests[i];
            if (forest == null) {
                continue;
            }

            GetWord word = forest.getWord(termAtt.buffer());
            while (word.getAllWords() != null) {
                String rawText = word.getParam(0);
                tokens.add(new CompoundToken(rawText));
            }
        }
    }
}
