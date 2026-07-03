
public abstract class DocumentFactory {

    // The Factory Method — subclasses decide which class to instantiate
    public abstract Document createDocument();

    // Optional: a template method that uses the factory method
    public Document openNewDocument() {
        Document doc = createDocument();
        doc.open();
        return doc;
    }
}