import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    public JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "/home/...";
    Image img;
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    int neww, newh;
    float brightenFactor = 1;

    public static void main(String[] args) {
        new ImageViewerGUI();
    }

    public ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
		
 		this.setLayout(null);


        mainPanel();
    }

    public void mainPanel(){

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2));
        buttonsPanel.setLayout(new GridLayout(2, 2));
		buttonsPanel.setBounds(50, 50, 600, 200);

       	selectFileButton = new JButton("selectFile");
		showImageButton = new JButton("showImage");
		closeButton = new JButton("close");

        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.closeButton);

		selectFileButton.addActionListener(this); 
		showImageButton.addActionListener(this); 
		closeButton.addActionListener(this); 


 
        this.add(buttonsPanel);

		this.setVisible(true);
		
 		this.setLayout(null);
		
    }

    

    public void chooseFileImage(){
        
        int r = fileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());

        
        h = imageIcon.getIconHeight();
        w = imageIcon.getIconWidth();

        img = imageIcon.getImage();

    }
    public void showOriginalImage(){

        if(img == null){
            return;
        }
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);

        
        ImageIcon newImage = new ImageIcon(img);
        JLabel label = new JLabel(newImage);
        label.setBounds((1500 - w) / 2, (800 - h) / 2, w, h);
        tempPanel.add(label);


        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        
    }

    

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== showImageButton){
            showOriginalImage();

        }else if(e.getSource()== selectFileButton){
            chooseFileImage();
        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}
