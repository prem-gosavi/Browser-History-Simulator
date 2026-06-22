import java.util.*;
public class BrowserHistory {
    Stack<String> history = new Stack<>();

    //add --- visited page
    public void visitPage(String page){
        history.push(page);
        System.out.println("Your visited page is......" + page);
    }

    //back 
    public void backPage(){
        if(history.isEmpty()){
            System.out.println("History Page is Empty..");
            return;
        }else{
            System.out.println("Going back from..." + history.pop());
        }
    }

    //currect -- page
    public void currentPage(){
        if(history.isEmpty()){
            System.out.println("History page is Empty");
            return;
        }else{
            System.out.println("Your Current History Page is..." + history.peek());
        }
    }

    //showallhistory
    public void showAllHistory(){
        if(history.isEmpty()){
            System.out.println("History is Empty you can't seen All History");
            return;
        }else{
            System.out.println("All Search History is..." + history);
        }   
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== Browser History ===");
            System.out.println("1. Visit Page");
            System.out.println("2. Back Page");
            System.out.println("3. Current Page");
            System.out.println("4. Show All History");
            System.out.println("5. Closed Your Browser");

            System.out.println("Enter your Choice :-");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Page Name...");
                    String page = sc.nextLine();

                    browser.visitPage(page);
                    break;
                case 2:
                    browser.backPage();
                    break;

                case 3:
                    browser.currentPage();
                    break;

                case 4:
                    browser.showAllHistory();
                    break;
                    
                case 5:
                    System.out.println("Your Browser is Closed");
                    sc.close();
                    return;
            
                default:
                   System.out.println("Invalid Choice");
            }
        }
    }
}
