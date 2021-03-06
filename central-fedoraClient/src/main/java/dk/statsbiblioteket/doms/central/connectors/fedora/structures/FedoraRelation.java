package dk.statsbiblioteket.doms.central.connectors.fedora.structures;

import dk.statsbiblioteket.doms.central.connectors.Connector;

/**
 * This object represent a relation, defined in the subject, with the predicate and "pointing" to the object. The object
 * can be a literal value, but in that case, the literal boolean must be set. Subject and predicate must not be null.
 *
 * Subject and, if not literal, object, shall be URIs, meaning that they should start with info:fedora/
 */
public class FedoraRelation {
    private String subject;
    private String predicate;
    private String object;

    private boolean literal;

    public FedoraRelation(String subject,
                          String predicate,
                          String object) {

        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getObject() {
        return object;
    }

    public boolean isLiteral() {
        return literal;
    }

    public void setLiteral(boolean literal) {
        this.literal = literal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FedoraRelation)) {
            return false;
        }

        FedoraRelation relation = (FedoraRelation) o;

        if (literal != relation.literal) {
            return false;
        }
        if (!Connector.toPid(object).equals(Connector.toPid(relation.object))) {
            return false;
        }
        if (!predicate.equals(relation.predicate)) {
            return false;
        }
        if (!Connector.toPid(subject).equals(Connector.toPid(relation.subject))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + predicate.hashCode();
        result = 31 * result + object.hashCode();
        result = 31 * result + (literal ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FedoraRelation{" +
               "subject='" + subject + '\'' +
               ", predicate='" + predicate + '\'' +
               ", object='" + object + '\'' +
               ", literal=" + literal +
               '}';
    }

}
