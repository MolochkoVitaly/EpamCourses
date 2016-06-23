package by.bsu.javacodeparser.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CompositeElement implements Component{
    private ArrayList<Component> content;
    private CompositeType type;

    public CompositeElement(CompositeType type) {
        content = new ArrayList<>();
        this.type = type;
    }

    public void add(Component component) {
        content.add(component);
    }

    @Override
    public List<Component> getContent() {
        return Collections.unmodifiableList(content);
    }

    @Override
    public CompositeType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case PACKAGE_OR_IMPORT:
                sb.append("\n");
                break;
            case OPERATOR:
                break;
            case FIELD:
                sb.append("\n    ");
                break;
            case METHOD:
                sb.append("\n\n");
                break;
            case BLOCK:
                sb.append("\n\n");
                break;
            case CLASS:
                sb.append("\n\n");
                break;
            case FILE:
                break;
            default:
                break;
        }
        appendComponents(sb);
        return sb.toString();
    }

    private void appendComponents(StringBuilder sb) {
        content.forEach(sb::append);
    }
}
