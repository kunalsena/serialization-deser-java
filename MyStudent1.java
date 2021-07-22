import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

 public class MyStudent1 extends JFrame implements ActionListener
 {
    public  JLabel l1,l2,l3,l4;
	public  JTextField t1,t2,t3;
	
	public  JTextArea ta;
	public JButton b1,b2;
	public   String name1,roll1,marks1;
	public   String name2,roll2,marks2;
    // constructor 

    MyStudent1(String st)
    {
        super(st);
        l1= new JLabel("Enter name");
        l2= new JLabel("Enter Roll No");
        l3= new JLabel("Enter Marks");
        l4= new JLabel("show details");
       
        //length of txt box & text area

        t1= new JTextField(10);
        t2= new JTextField(10);
        t3= new JTextField(10);
        ta= new JTextArea(20,20); // length & width
        b1 = new JButton("SUBMIT");
        b2= new JButton("SHOW");
        // this indicate this class
        b1.addActionListener(this);
        b2.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });


        JPanel p1= new JPanel();
        JPanel p2= new JPanel();
        JPanel p3= new JPanel();

        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p3.setLayout(new FlowLayout());

        p1.add(l1); p1.add(t1); p1.add(l2); p1.add(t2);
        p2.add(l3); p2.add(t3); p2.add(b1);
        p3.add(l4); p3.add(ta); p3.add(b2);


        Container c = getContentPane();
        c.add(p1,BorderLayout.NORTH);
        c.add(p2,BorderLayout.CENTER);
        c.add(p3,BorderLayout.SOUTH);
        setSize(600,600);
        setVisible(true);
    }
    public static void main(String[] args) {
        MyStudent1 mm= new MyStudent1("My Student Form");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub override the method

        if(e.getSource()==b1){
            name2 = t1.getText();
            roll2 = t2.getText();
            marks2 = t3.getText();
            
            // now serilization data
           serilization();
        }
        
        else if(e.getSource()==b2)
       {
            
//deserilized data first
            deserilization();

            //display it now

            ta.append(name1);
            ta.append(roll1);
            ta.append(marks1);
        }
    }

    // SERILZATION AND DE-SERILAZATION
  public void serilization()
   {

    try {

        String name,roll,marks;
        ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream("ABC.txt"));
        
        name= name2;
        roll= roll2;
        marks=marks2;
        //creating student  class object
        
        student1 s = new student1(name, roll, marks);
        
        objout.writeObject(s);
        objout.close();
    } 
    catch (Exception e) {
        //TODO: handle exception
    }

   }
   // END OF SERILIZATION
    
   public void deserilization()
   {
       try {
           ObjectInputStream objin= new ObjectInputStream(new FileInputStream("ABC.txt"));
            student1 s = (student1)objin.readObject();//EXPLICTE TYPECASIING HERE AS DATA WILL READ AS AN AOBJECT

            name1 = s.name;
            roll1= s.roll;
            marks1=s.marks;

            objin.close();

       } 
       catch (Exception e) {
           //TODO: handle exception
       }
       
   }

}

class student1 implements Serializable
{
    String name;
    String roll;
    String marks;

    // constructor 

    public student1(String name, String roll, String marks)
    {
        this.name= name;
        this.roll= roll;
        this.marks= marks;
    }
}