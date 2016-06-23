package by.bsu.javacodeparser.parser;

import by.bsu.javacodeparser.entity.CompositeType;
import by.bsu.javacodeparser.entity.Leaf;
import by.bsu.javacodeparser.entity.LeafType;
import by.bsu.javacodeparser.storage.LiteralsAndKeyWords;

public class PackageAndImportHandler extends AbstractHandler {
    private static PackageAndImportHandler packageImportParser = new PackageAndImportHandler(CompositeType.PACKAGE_OR_IMPORT);

    public PackageAndImportHandler(CompositeType type) {
        super(type);
    }

    public static PackageAndImportHandler getPackageImportParser() {
        return packageImportParser;
    }

    @Override
    public void parse(String text) {
        String[] leafs = text.split("\\s+");
        for (String leaf : leafs) {
            if (LiteralsAndKeyWords.getKeyWords().contains(leaf)) {
                element.add(new Leaf(leaf, LeafType.KEY_WORD));
            } else {
                element.add(new Leaf(leaf, LeafType.LEXEME));
            }
        }
    }
}
