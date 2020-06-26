import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class TIC_TAC_TOE extends JFrame
{
	
  private Container pane;
  private String currPlayer;
  private JButton board[][];
  private boolean IsWinner;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem quit;
  private JMenuItem Restart;
  static int k=0;
  
  public TIC_TAC_TOE() {
	  
	  super();
	  pane=getContentPane();
	  pane.setLayout(new GridLayout(3,3,5,5));
	  setTitle("TIC TAC TOE");
	  setSize(600, 600);
	  setResizable(false);
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setVisible(true);
	  currPlayer="X";
	  board=new JButton [3][3];
	  IsWinner=false;
	  Initialize_Board() ;
	  Initialize_Menu_Bar();
  }
  
  private void Initialize_Menu_Bar() {
	  
	  menuBar= new JMenuBar();
	  menu= new JMenu("OPTIONS");
	  Restart= new JMenuItem("RESTART GAME");
	  
	  Restart.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {	
			Reset_Board();
		}
	});
	  
	  quit= new JMenuItem("QUIT GAME");
	  
	  quit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	  
	menu.add(Restart); 
	menu.add(quit);  
	menuBar.add(menu);
	menuBar.setBackground(Color.PINK);
	setJMenuBar(menuBar);

  }
  

  private void Reset_Board() {
	 
	int i,j;
	
	currPlayer="X";
	IsWinner=false;
	
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		
			board[i][j].setText("");
	}  
  }
  
  private void Initialize_Board() {
	  
	int i,j;  
    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		{
			JButton button=new JButton();
			button.setFont(new Font(Font.SANS_SERIF,Font.BOLD,70));
			button.setBackground(Color.LIGHT_GRAY);
			button.setBorder(raisedbevel);
			board[i][j]=button;
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(((JButton)e.getSource()).getText().equals("")==true && IsWinner==false)
					{
						button.setText(currPlayer);
						Check_For_Winner();
						if(currPlayer.equals("X"))
							currPlayer="O";
						else 
							currPlayer="X";
					}
				}
			});
			
			pane.add(button);
			
		}
	}   
  }
  
 
  private void Check_For_Winner() {
	  
	  int q10=0,q11=0,q12=0,q22=0,q3=0,q4=0,q21=0,q20=0,q=0,i,j;
	  
	  k++;
	  
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				
				if(board[0][j].getText().equals(currPlayer))//FIRST ROW
					q10++;

				if(board[1][j].getText().equals(currPlayer))//SECOND ROW
					q11++;

				if(board[2][j].getText().equals(currPlayer))//THIRD ROW
					q12++;

				if(board[j][0].getText().equals(currPlayer))//FIRST COLUMN
					q20++;

				if(board[j][1].getText().equals(currPlayer))//SECOND COLUMN
					q21++;

				if(board[j][2].getText().equals(currPlayer))//THIRD COLUMN
					q22++;


				if(i==j)//LEFT DIAGONAL
				{
					if(board[i][j].getText().equals(currPlayer))
						q3++;
				}

				if((i+j)==2)//RIGHT DIAGONAl
				{
					if(board[i][j].getText().equals(currPlayer))
						q4++;
				}

				if(q10==3 || q11==3 || q12==3 || q20==3 ||q21==3||q22==3||q3==3||q4==3)
				{
					q=1;
					break; 
				}
				

			}
			
			if(q==1)
				break;
			
			 q10=0;q11=0;q12=0;q22=0;q21=0;q20=0;
		}
		
		if(q==1)
		{
			if(q10==3 || q11==3 || q12==3 || q20==3 ||q21==3||q22==3||q3==3||q4==3)
			{
				JOptionPane.showMessageDialog(null, "PLAYER " + " '"+currPlayer +"' "+ " HAS WON!!");
	            IsWinner = true;
			}
			
		}

		
		else if(k==9)
		{
			JOptionPane.showMessageDialog(null, "IT'S A DRAW!!");
            IsWinner = true;
		}

  }
  
}
