package prelim;
public class SimpleTestApp {
    public static void main(String[] args) {
        System.out.println(ConsoleUI.title("Testing All Data Structures"));
// Test 1: Fixed Size Array List
        System.out.println(ConsoleUI.section("1. Testing MyFixedSizeArrayList"));
        testFixedSizeArrayList();
// Test 2: Growing Array List
        System.out.println(ConsoleUI.section("2. Testing MyGrowingArrayList"));
        testGrowingArrayList();
// Test 3: Singly Linked List
        System.out.println(ConsoleUI.section("3. Testing MySinglyLinkedList"));
        testSinglyLinkedList();
// Test 4: Doubly Linked List
        System.out.println(ConsoleUI.section("4. Testing MyDoublyLinkedList"));
        testDoublyLinkedList();
// Test 5: Singly Linked Circular List
        System.out.println(ConsoleUI.section("5. Testing MySinglyLinkedCircularList"));
        testSinglyLinkedCircularList();
// Test 6: Doubly Linked Circular List
        System.out.println(ConsoleUI.section("6. Testing MyDoublyLinkedCircularList"));
        testDoublyLinkedCircularList();
    }
    private static void testFixedSizeArrayList() {
        MyFixedSizeArrayList<String> list = new MyFixedSizeArrayList<>();
        try {
            list.insert("Apple");
            list.insert("Banana");
            list.insert("Cherry");
            list.display();
            System.out.println("Searching for 'Banana': Index " + list.search("Banana"));
            list.delete("Banana");
            System.out.println("After deleting 'Banana':");
            list.display();
// Try to fill the list
            list.insert("Date");
            list.insert("Elderberry");
            list.insert("Fig"); // This should cause overflow
        } catch (ListOverflowException e) {
            System.out.println("Overflow caught: " + e.getMessage());
        }
        System.out.println("Final state:");
        list.display();
    }
    private static void testGrowingArrayList() {
        MyGrowingArrayList<Integer> list = new MyGrowingArrayList<>();
        try {
// Insert more than initial capacity to test growing
            for (int i = 1; i <= 8; i++) {
                list.insert(i * 10);
                System.out.println("Inserted " + (i * 10));
            }
            list.display();
            System.out.println("Size: " + list.getSize());
            list.delete(30);
            System.out.println("After deleting 30:");
            list.display();
        } catch (ListOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    private static void testSinglyLinkedList() {
        MySinglyLinkedList<String> list = new MySinglyLinkedList<>();
        list.insert("First");
        list.insert("Second");
        list.insert("Third");
        list.insert("Fourth");
        list.display();
        System.out.println("Searching for 'Third': Index " + list.search("Third"));
        list.delete("Second");
        System.out.println("After deleting 'Second':");
        list.display();
    }
    private static void testDoublyLinkedList() {
        MyDoublyLinkedList<Character> list = new MyDoublyLinkedList<>();
        list.insert('A');
        list.insert('B');
        list.insert('C');
        list.insert('D');
        list.display();
        System.out.println("Searching for 'C': Index " + list.search('C'));
        list.delete('B');
        System.out.println("After deleting 'B':");
        list.display();
    }
    private static void testSinglyLinkedCircularList() {
        MySinglyLinkedCircularList<String> list = new MySinglyLinkedCircularList<>();
        list.insert("Monday");
        list.insert("Tuesday");
        list.insert("Wednesday");
        list.insert("Thursday");
        list.display();
        System.out.println("Searching for 'Wednesday': Index " + list.search("Wednesday"));
        list.delete("Tuesday");
        System.out.println("After deleting 'Tuesday':");
        list.display();
    }
    private static void testDoublyLinkedCircularList() {
        MyDoublyLinkedCircularList<Integer> list = new MyDoublyLinkedCircularList<>();
        list.insert(100);
        list.insert(200);
        list.insert(300);
        list.insert(400);
        list.display();
        System.out.println("Searching for 300: Index " + list.search(300));
        list.delete(200);
        System.out.println("After deleting 200:");
        list.display();
        System.out.println("Final size: " + list.getSize());
    }
}