package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Term {
    int termFrequency;
    LinkedList<TermDocument> list;

    public Term(int docID) {
        termFrequency = 1;
        list = new LinkedList<TermDocument>();
        TermDocument termDocument = new TermDocument(docID);
        list.add(termDocument);
    }

    void addDocument(int docID) {
        if (this.list.getLast().getDocID() == docID) {
            this.termFrequency++;
        } else {
            this.list.getLast().increaseFrequency();
        }
    }

    void print() {
        for (Iterator I = this.list.iterator();
             I.hasNext();) {
            System.out.print(I.next() + " ");
        }
        System.out.println("");
    }
    //public Term(int docID) – конструктор класса устанавливает начальную частоту терма в коллекции, добавляет в список новый экземпляр класса TermDocument;
    //public void addDocument(int docID) – увеличивает частоту терма в коллекции. Сначала необходимо проверить встречался ли данный терм в документе с идентификатором docID. Для этого нужно проверить последний объект в списке list, если в списке уже есть документ с идентификатором docID, то нужно увеличить частоту с помощью метода increaseFrequency(). Если терм встретился в новом документе, т.е. он не содержится в списке list), то в таком случае нужно добавить новый экземпляр класса TermDocument в список;
    public void computeTfIdf(double idf) {       //метод должен вызывать computeTfIdf(idf) для каждого экземпляра класса TermDocument из списка list;
        Iterator<TermDocument> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next().computeTfIdf(idf);
        }
    }
    public int getDocumentFrequency() {      //возвращает количество документов, в которые входит терм (размерность list);
        return list.size();
    }
    public List<TermDocument> getList(){
        return list;
    }
}
