package homework;

import homework.model.MyList;

public class Main {
    public static void main(String[] args) {
        MyList myList = new MyList(3);
        myList.add(6);
        myList.add(7);
        myList.add(8);
        myList.add(9);
        myList.add(10);
        Integer integer = 7;
        System.out.println(myList.remove(integer));
        System.out.println(myList.remove(1));
        System.out.println(myList.isEmpty());
        System.out.println(myList.contains(7));
        System.out.println(myList.indexOf(10));
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }
}