package panels;

import data_transfer.InitData;
import data_transfer.TalkServlet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

// GridbagLayout for 4 video thumbnails
public class MenuVideo extends JPanel{
    private JButton vid1, vid2, vid3, vid4;
    static int vidID;
    private InitData initData1;
    private InitData initData2;
    private InitData initData3;
    private InitData initData4;
    BufferedImage bImage;

    public MenuVideo(){
        bImage = null;
        initData1 = TalkServlet.getInitData1();
        initData2 = TalkServlet.getInitData2();
        initData3 = TalkServlet.getInitData3();
        initData4 = TalkServlet.getInitData4();
        ByteArrayInputStream bis = new ByteArrayInputStream(initData1.getImageByte());

        ProgressBar prog1 = new ProgressBar(initData1.getProgress().get(1),initData1.getProgress().get(0));
        ProgressBar prog2 = new ProgressBar(initData2.getProgress().get(1),initData2.getProgress().get(0));
        ProgressBar prog3 = new ProgressBar(initData3.getProgress().get(1),initData3.getProgress().get(0));
        ProgressBar prog4 = new ProgressBar(initData4.getProgress().get(1),initData4.getProgress().get(0));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon scaledImage = new ImageIcon(bImage.getScaledInstance(200,113,Image.SCALE_DEFAULT));
        vid1 = new JButton(scaledImage);
        vid1.setBorder(BorderFactory.createEmptyBorder());
        vid1.setContentAreaFilled(false);

        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        add(vid1,c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx=1;
        c.weighty=1;
        add(prog1,c);


        bis = new ByteArrayInputStream(initData2.getImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scaledImage = new ImageIcon(bImage.getScaledInstance(200,113,Image.SCALE_DEFAULT));
        vid2 = new JButton(scaledImage);
        vid2.setBorder(BorderFactory.createEmptyBorder());
        vid2.setContentAreaFilled(false);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        add(vid2,c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx=1;
        c.weighty=1;
        add(prog2,c);

        bis = new ByteArrayInputStream(initData3.getImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scaledImage = new ImageIcon(bImage.getScaledInstance(200,113,Image.SCALE_DEFAULT));
        vid3 = new JButton(scaledImage);
        vid3.setBorder(BorderFactory.createEmptyBorder());
        vid3.setContentAreaFilled(false);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx=1;
        c.weighty=1;
        add(vid3,c);

        c.gridx = 0;
        c.gridy = 3;
        c.weightx=1;
        c.weighty=1;
        add(prog3,c);

        bis = new ByteArrayInputStream(initData4.getImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scaledImage = new ImageIcon(bImage.getScaledInstance(200,113,Image.SCALE_DEFAULT));
        vid4 = new JButton(scaledImage);
        vid4.setBorder(BorderFactory.createEmptyBorder());
        vid4.setContentAreaFilled(false);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx=1;
        c.weighty=1;
        add(vid4,c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx=1;
        c.weighty=1;
        add(prog4,c);

        vidID = 1; // default open video 1

        /*vid1 = new JButton("Video 1");

        //ImageIcon ant1 = new ImageIcon(getClass().getClassLoader().getResource("./vid_1/00001.png"));
        //int offset =  vid1.getInsets().left;
        //vid1.setIcon(resizeIcon(ant1,vid1.getWidth()-offset,vid1.getHeight()-offset));
        //vid1 = new JButton(ant1);
        //vid1 = new JButton("video 1");
        vid2 = new JButton("Video 2");
        vid3 = new JButton("Video 3");
        vid4 = new JButton("Video 4");

        // BufferedImage ant1 = new BufferedImage(Objects.requireNonNull(getClass().getClassLoader().getResource(path)));

        // button layout
        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        add(vid1 ,c);


        c.gridx = 1;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        add(vid2 ,c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx=1;
        c.weighty=1;
        add(vid3 ,c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx=1;
        c.weighty=1;
        add(vid4 ,c);*/

        //revalidate();
        //repaint();
        setSize(200,200);

        // Add button actions : return videoID
        vid1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 1;
                //System.out.println("Clicked");
                //getVidID(vidID);
            }
        });

        vid2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 2;
                //System.out.println("Clicked2");
                //getVidID(vidID);
            }
        });

        vid3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 3;
                //System.out.println("Clicked3");
                //getVidID(vidID);
            }
        });

        vid4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 4;
                //System.out.println("Clicked4");
                //getVidID(vidID);
            }
        });
    }

    public static String getVidID(){
        String video = "vid_" + vidID;
        return video;
    }

    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

}