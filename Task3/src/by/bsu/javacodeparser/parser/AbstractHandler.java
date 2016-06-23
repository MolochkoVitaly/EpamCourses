package by.bsu.javacodeparser.parser;

import by.bsu.javacodeparser.entity.CompositeElement;
import by.bsu.javacodeparser.entity.CompositeType;


public abstract class AbstractHandler {
    protected AbstractHandler successor;
    protected CompositeElement element;
    private CompositeType type;

    public AbstractHandler(CompositeType type) {
            this.type = type;
        }

    public abstract void parse(String text);

    public CompositeElement chain(String text) {
        element = new CompositeElement(type);
        parse(text);
        return element;
    }

    public void setSuccessor(AbstractHandler successor) {
            this.successor = successor;
        }
}



