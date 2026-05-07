import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
class calculation
{
	int stack[] = new int[20];
	int top = -1;
	void push(int x)
	{
		if(top==19)
			System.out.println("Stack is full");
		else
			stack[++top] = x;
	}
	int pop()
	{
		if(top==-1)
		{
			System.out.println("Stack is empty");
			return -1;
		}
		else
			return stack[top--];
	}
	int prcd(char symbol)
	{
		switch(symbol)
		{
		case '+':
		case '-':return 2;	
		case '*':
		case '/':return 4;	
		case '%':	
		case '$':	
		case '^':return 6;	
		case '(':	
		case ')':return 0;	
		default:
			return -1;
		}
	}
	int isoperand(char symbol)
	{
		if((symbol>='A'&&symbol<='Z')||(symbol>='a'&&symbol<='z')||(symbol>='0'&&symbol<='9'))
		return 1;
		else
		return 0;
	}
	String converttopostfix(String infix)
	{
		int i,j=0;
		char symbol;
		String postfix = new String();
		for(i=0;i<infix.length();i++)
		{
			symbol=infix.charAt(i);
			if(isoperand(symbol)==1)
			{
				postfix = postfix + symbol;
				j++;
			}
			else if(symbol=='(')
			{
				push(symbol);
			}
			else if(symbol==')')
			{
				while(stack[top]!='(')
				{
					postfix = postfix + pop();
					j++;
				}
				pop();
			}
			else if(isoperator(symbol)==1)
			{
				if(top==-1||prcd(symbol)>prcd((char)stack[top]))
				{
					push(symbol);
				}
				else 
				{
					while(top!=-1 && prcd(symbol)<=prcd((char)stack[top]))
					{
						postfix = postfix + (char)pop();
						j++;
					}
					push(symbol);
				}
			}
			else
			{
				System.out.println("invalid operator");
				System.exit(0);
			}
		}
		while(top!=-1)
		{
			postfix = postfix + (char)pop();
			j++;
		}
		return postfix;
	}
	

	//evaluation of postfix expression
	int isoperator(char symbol)
	{
		switch(symbol)
		{
			case '+':
			case '-':
			case '*':
			case '/':return 1;
			default :return 0;
		}
	}
	int isdigits(char c)
	{
		if(c>='0'&&c<='9')
			return 1;
		else
			return 0;
	}
	int evaluate(String postfix)
	{
		int i,op1,op2,result=0;
		char symbol;
		for(i=0;i<postfix.length();i++)
		{
			symbol=postfix.charAt(i);
			while(symbol==' '|| symbol=='\t')
			{
				i++;

				if(i < postfix.length())
					symbol = postfix.charAt(i);
			}
			if(isdigits(symbol)==1)
			{
				push(symbol-48);
			}
			else if(isoperator(symbol)==1)
			{
				op1=pop();
				op2=pop();
				switch(symbol)
				{
					case '+':
						result=op2+op1;
					    break;
					case '-':
						result=op2-op1;
				   		break;
					case '*':
						result=op2*op1 ;
					    break;
					case '/':
						result=op2/op1 ;
					    break;
						default:
					    System.out.println("invalid operator!");
						System.exit(0);	
				}
				push(result);
			}
			else
			{
				System.out.println("invalid operator!");
				System.exit(0);
			}
		}
		result=pop();
		return result;
	}

}
class Calculator extends Frame implements ActionListener,ItemListener,WindowListener
{
	Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,badd,bsub,bdiv,bmul,bequal,bcls;
	TextArea txtaDisplay;
	Font f1,f2;
	Panel btnp;
	Calculator()
	{
		//calling constructor
		f1 = new Font("Arial",Font.BOLD,20);
		f2 = new Font("Arial",Font.BOLD,30);
		setSize(600,400);
		setResizable(false);
		setTitle("Simple Calculator");
		setLayout(new BorderLayout());
		b0 = new Button("0");
		b1 = new Button("1");
		b2 = new Button("2");
		b3 = new Button("3");
		b4 = new Button("4");
		b5 = new Button("5");
		b6 = new Button("6");
		b7 = new Button("7");
		b8 = new Button("8");
		b9 = new Button("9");
		badd = new Button("+");
		bsub = new Button("-");
		bmul = new Button("*");
		bdiv = new Button("/");
		bequal = new Button("=");
		bcls = new Button("clr");
		txtaDisplay = new TextArea(6,4);
		txtaDisplay.setFont(f1);
		btnp = new Panel();
		btnp.setFont(f2);
		btnp.setLayout(new GridLayout(4,4,5,5));

		//adding componets
		add(txtaDisplay,BorderLayout.NORTH);
		btnp.add(b7);
		btnp.add(b8);
		btnp.add(b9);
		btnp.add(bdiv);
		btnp.add(b4);
		btnp.add(b5);
		btnp.add(b6);
		btnp.add(bmul);
		btnp.add(b1);
		btnp.add(b2);
		btnp.add(b3);
		btnp.add(bsub);
		btnp.add(bcls);
		btnp.add(b0);
		btnp.add(bequal);
		btnp.add(badd);
		add(btnp,BorderLayout.CENTER);
		
		//register listener
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		badd.addActionListener(this);
		bsub.addActionListener(this);
		bdiv.addActionListener(this);
		bmul.addActionListener(this);
		bcls.addActionListener(this);
		bequal.addActionListener(this);
		addWindowListener(this);

		setVisible(true);

	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==b0)
			txtaDisplay.setText(txtaDisplay.getText() +"0");
		else if(ae.getSource()==b1)
			txtaDisplay.setText(txtaDisplay.getText() +"1");
		else if(ae.getSource()==b2)
			txtaDisplay.setText(txtaDisplay.getText() +"2");
		else if(ae.getSource()==b3)
			txtaDisplay.setText(txtaDisplay.getText() +"3");
		else if(ae.getSource()==b4)
			txtaDisplay.setText(txtaDisplay.getText() +"4");
		else if(ae.getSource()==b5)
			txtaDisplay.setText(txtaDisplay.getText() +"5");
		else if(ae.getSource()==b6)
			txtaDisplay.setText(txtaDisplay.getText() +"6");
		else if(ae.getSource()==b7)
			txtaDisplay.setText(txtaDisplay.getText() +"7");
		else if(ae.getSource()==b8)
			txtaDisplay.setText(txtaDisplay.getText() +"8");
		else if(ae.getSource()==b9)
			txtaDisplay.setText(txtaDisplay.getText() +"9");
		else if(ae.getSource()==badd)
			txtaDisplay.setText(txtaDisplay.getText() +"+");
		else if(ae.getSource()==bsub)
			txtaDisplay.setText(txtaDisplay.getText() +"-");
		else if(ae.getSource()==bmul)
			txtaDisplay.setText(txtaDisplay.getText() +"*");
		else if(ae.getSource()==bdiv)
			txtaDisplay.setText(txtaDisplay.getText() +"/");

		else if(ae.getSource()==bcls)
			txtaDisplay.setText("");
		else if(ae.getSource()==bequal){
			String eq = txtaDisplay.getText();
			calculation c = new calculation();
			String res = String.valueOf(c.evaluate(c.converttopostfix(eq)));
			txtaDisplay.setText(String.valueOf(res));
		}

	}
	public void itemStateChanged(ItemEvent ie) {}
	public void windowActivated(WindowEvent we){}
	public void windowClosed(WindowEvent we){}
	public void windowClosing(WindowEvent we){
		dispose();
	}
	public void windowDeactivated(WindowEvent we){}
	public void windowDeiconified(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowOpened(WindowEvent we){}

}
class awtcal
{
	public static void main(String[] args) {
		new Calculator();
	}
}








