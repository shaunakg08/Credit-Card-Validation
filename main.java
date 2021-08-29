import java.util.Scanner;
class Validate_Credit_Card
{   static String cnn;
    static int a;
    private static Scanner sc;
  
    
    //function to check whether there are alphabets in the number and eliminate them;
    private static void checkalpha()                       
    {  
        int x=0; String t="";
        for(;x<a;x++)
        {  char c=cnn.charAt(x);
            if(c>='0' && c<='9')
                t=t+c;
        }
        cnn=t;

    } //end of checkalpha()

    //function to identify Major Industry Identifier(MII)
    private static void mii()                              
    {   char c=cnn.charAt(0);
        String m="";
        switch(c)
        {
            case '1':
            case '2':
            m="Airlines";
            break;

            case '3':
            m="Travel";
            break;

            case '4':
            case '5':
            m="Banking and Financial";
            break;

            case '6':
            m="Merchandising and Banking / Financial";
            break;
            case '7':
            m = "Petroleum";
            break;

            case '8':
            m="Healthcare,Telecommunications";
            break;

            case '9':
            case '0':
            m="Unknown";
            break;
        }

        System.out.println("Major Industry Identifier (MII) : "+m);
    } //end of mii()

    
    //function to identify Issuer Identification Number (IIN)
    private static void iin()                              
    {  
        String in=cnn.substring(0,5),info="";
        if(in.startsWith("34")==true || in.startsWith("37")==true)
            info="Amex";
        else if(in.startsWith("4"))
            info="Visa";
        else if(in.startsWith("51") || in.startsWith("52") || in.startsWith("53") ||in.startsWith("54") || in.startsWith("55"))
            info="MasterCard";
        else if(in.startsWith("6011") || in.startsWith("644") || in.startsWith("65"))
            info="Discover";
        else
            info="Unknown";

        System.out.println("Issuer Identification Number (IIN) : "+info);

    } //end of iin()

    
    //functon to extract the account number
    private static void accno()                                   
    { 
        String acn=cnn.substring(6,cnn.length()-1);
        System.out.println("Account Number : "+acn);
    } //end of accno()

    
    /*function to extract digit at odd places and take out the sum using mod10 algorithm*/
    private static int mod10algoodd()               
    {  
        int p, sum=0 ;
        for(int x=a-2;x>=0;x=x-2)
        {   char c =cnn.charAt(x);
            p=2*(Integer.parseInt(c+""));
            if(p>=10)
                p=p-9;

            sum=sum+p;

        }

        return sum; 

    } //end of mod10algoodd()

    //function to extract digit at eve places and take out the sum using mod10 algorithm
    private static int mod10algoeve()    
    {    int p, sum=0 ;
        for(int x=a-1;x>=0;x=x-2)
        {   char c =cnn.charAt(x);
            p=(Integer.parseInt(c+""));
            if(p>10)
                p=p-9;

            sum+=p;
        }

        return sum;

    }//end of mod10lalgoeve()

    //extract check digit i.e. the last digit from the string
    private static char chkdigit()                
    {   
        char chkd=cnn.charAt(cnn.length()-1);   
        return chkd;
    }//end of chkdigit()

    
    //display by calling functions if card is valid
    private static void show()                    
    {   checkalpha();
        mii();
        iin();
        accno();
        System.out.println("Check digit is = "+chkdigit());
    } //end of show()

    
    //input the card number and check if it valid or not and display respective message
    public static void main(String args[])                   
    {   sc = new Scanner(System.in);
        System.out.println("Enter Credit Card Number: ");
        cnn=sc.nextLine();
        a=cnn.length();
        if(a<=12 && a>=19)
            System.out.println("The Card is Invalid");
        else
        {   if((mod10algoeve()+mod10algoodd())%10==0)
                show();

            else
            {  
                
                System.out.println("The Card in Invalid");
                System.out.println("check digit is = "+chkdigit());
            }
        }
    }//end of main()
    
    }//end of class  
 