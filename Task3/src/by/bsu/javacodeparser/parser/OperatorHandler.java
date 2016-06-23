package by.bsu.javacodeparser.parser;


import by.bsu.javacodeparser.storage.LexemeList;
import by.bsu.javacodeparser.storage.OperatorList;
import by.bsu.javacodeparser.entity.CompositeType;
import by.bsu.javacodeparser.entity.Leaf;
import by.bsu.javacodeparser.entity.LeafType;
import by.bsu.javacodeparser.storage.LiteralsAndKeyWords;
import by.bsu.javacodeparser.util.RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OperatorHandler extends AbstractHandler {
    private static OperatorHandler operatorParser = new OperatorHandler(CompositeType.OPERATOR);

    public OperatorHandler(CompositeType type) {
        super(type);
    }

    public static OperatorHandler getOperatorParser() {
        return operatorParser;
    }

    @Override
    public void parse(String text) {
        Pattern pattern = Pattern.compile(RegEx.WORD);
        Matcher matcher = pattern.matcher(text.trim());

        OperatorList.addOperator(text.trim());

        while (matcher.find()) {
            String leaf = matcher.group();

            LexemeList.addLexeme(leaf);

            if (leaf.equals("{")) {
                element.add(new Leaf(leaf, LeafType.BEGIN_BODY));
            } else if (leaf.equals("}")) {
                element.add(new Leaf(leaf, LeafType.END_BODY));
            } else  if (LiteralsAndKeyWords.getKeyWords().contains(leaf)) {
                element.add(new Leaf(leaf, LeafType.KEY_WORD));
            } else if (LiteralsAndKeyWords.getLiterals().contains(leaf)) {
                element.add(new Leaf(leaf, LeafType.LITERAL));
            } else {
                element.add(new Leaf(leaf, LeafType.LEXEME));
            }
        }
    }
}
