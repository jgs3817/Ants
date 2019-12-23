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
import java.util.Arrays;
import java.util.Objects;

// GridbagLayout for 4 video thumbnails
public class MenuVideo extends JPanel{
    private JButton vid1, vid2, vid3, vid4;
    static int vidID;
    private InitData initData;
    BufferedImage bImage;

    public MenuVideo(){
        /*bImage = null;
        initData = TalkServlet.getInitData();
        ByteArrayInputStream bis = new ByteArrayInputStream(initData.getImageByte());
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int w = bImage.getWidth();
        int h = bImage.getHeight();
        BufferedImage scaledImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(1,1);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        scaledImage = scaleOp.filter(bImage, scaledImage);
        setLayout(new GridLayout(0,2));
        vidID = 1; // default open video 1
        vid1 = new JButton(new ImageIcon(scaledImage));
        vid1.setBorder(BorderFactory.createEmptyBorder());
        vid1.setContentAreaFilled(false);
        vid2 = new JButton("Video 2");
        vid3 = new JButton("Video 3");
        vid4 = new JButton("Video 4");
        add(vid1);
        add(vid2);
        add(vid3);
        add(vid4);*/

        //System.out.println("Menu video constructor called");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        vidID = 1; // default open video 1

        vid1 = new JButton("Video 1");

        /*ImageIcon ant1 = new ImageIcon(getClass().getClassLoader().getResource("./vid_1/00001.png"));
        int offset =  vid1.getInsets().left;
        vid1.setIcon(resizeIcon(ant1,vid1.getWidth()-offset,vid1.getHeight()-offset));
        vid1 = new JButton(ant1);*/
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
        add(vid4 ,c);

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
                //getVidID(vidID);
            }
        });

        vid3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 3;
                //getVidID(vidID);
            }
        });

        vid4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vidID = 4;
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