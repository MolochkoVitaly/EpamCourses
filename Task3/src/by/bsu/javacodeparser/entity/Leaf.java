package by.bsu.javacodeparser.entity;


public class Leaf implements Component {
    private String content;
    private LeafType type;

    public Leaf(String content, LeafType type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public CompositeType getType() {
        return null;
    }

    public LeafType getLeafType() {
        return type;
    }

    @Override
    public String toString() {
        String result = "";
        switch (type) {
            case BEGIN_BODY:
                //result += "\n";
                result = content;
                break;
            case KEY_WORD:
                result = content +  " ";
                break;
            case LITERAL:
                result = content +  " ";
                break;
            case LEXEME:
                result = content +  " ";
                break;
            case END_BODY:
                result = "\n" + content ;
                break;
            default:
                break;
        }
        return result;
    }
}
