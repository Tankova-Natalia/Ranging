package com.company;

public class TermDocument {
    private int docID;      // идентификатор документа, в котором встретился терм;
    private int tf;         // количество вхождений терма в документ;
    private double tf_idf;  // вес терма в документе;

    public TermDocument(int docID){     //конструктор класса,задает идентификатор документа и устанавливает начальную частоту;
        this.docID = docID;
        tf = 1;
    }
    public int getDocID() {     //возвращает идентификатор документа;
        return docID;
    }
    public void increaseFrequency() {       //увеличивает частоту терма для данного документа;
        tf++;
    }

    public void computeTfIdf(double idf) {       //вычисляет tf-idf;
        tf_idf = (1 + Math.log10(tf)) * idf;
    }
    public double getTfIdf() {       //возвращает tf-idf;
        return tf_idf;
    }
    public String toString() {       //возвращает строковое представление экземпляра класса.
        return "docID: " + docID + "\n" +
                "tf: " + tf + "\n" +
                "tf_idf: " + tf_idf + "\n";
    }
}
