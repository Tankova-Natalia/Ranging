package com.company;

public class DocumentRelevance {
    private int docID;          //идентификатор документа;
    private double relevance;   //степень соответствия документа запросу;
    public DocumentRelevance(int docID) {    //конструктор с параметром задает идентификатор документа;
        this.docID = docID;
    }
    public int getDocID() {      //возвращает идентификатор документа;
        return this.docID;
    }
    public double getRelevance() {       //возвращает меру соответствия документа запросу;
        
    }
    public void updateRelevance(double tf) {     //увеличивает релевантность текущего документа на значение tf;
    }
    public String toString() {       //метод преобразования объекта к строке, возвращает идентификатор документа и релевантность
    }
}
