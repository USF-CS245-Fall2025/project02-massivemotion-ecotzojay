import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for the Massive Motion simulation.
 * This class loads configuration, creates the window, and manages
 * the animation for the celestial objects
 */
public class MassiveMotion extends JPanel implements ActionListener {
    private List<CelestialObject> celestialObject; //Master list to hold all objects
    private Timer animationTimer;
    private Random cometGenerator;
    private Properties properties;
    //Window properties
    private int windowWidth;
    private int windowHeight;
    //Comet properties
    private double genX, genY;
    private int bodySize;
    private int bodyVelocityRange;

    /**
     * Constructor for the Massive Motion simulation.
     * Loads all properties from the configuration file, initializes the List implementation
     * and adds the start to the simulation.
     * @param propertyFileName The name of the .txt file to load
     */
    public MassiveMotion(String propertyFileName) {
        this.cometGenerator = new Random();
        this.properties = new Properties();

        //Read a property file
        try {
            FileInputStream fileInput = new FileInputStream(propertyFileName);
            properties.load(fileInput);
            fileInput.close();
        } catch (IOException e) {
            System.err.println("Error: Could not load property file.");
            e.printStackTrace();
            System.exit(1);
        }

        //Load properties into variables
        int timerDelay = Integer.parseInt(properties.getProperty("timer_delay"));
        this.windowWidth = Integer.parseInt(properties.getProperty("window_size_x"));
        this.windowHeight = Integer.parseInt(properties.getProperty("window_size_y"));

        String listType = properties.getProperty("list");

        if (listType.equals("arraylist")) {
            this.celestialObject = new ArrayList<>();
        } else if (listType.equals("single")) {
            this.celestialObject = new LinkedList<>();
        } else if (listType.equals("double")) {
            this.celestialObject = new DoublyLinkedList<>();
        } else if (listType.equals("dummyhead")) {
            this.celestialObject = new DummyHeadLinkedList<>();
        } else {
            System.err.println("Error: Unknown list type. Defaulting to ArrayList.");
            this.celestialObject = new ArrayList<>();
        }

        //Create star based on properties
        double starX = Double.parseDouble(properties.getProperty("star_position_x"));
        double starY = Double.parseDouble(properties.getProperty("star_position_y"));
        double starVX = Double.parseDouble(properties.getProperty("star_velocity_x"));
        double starVY = Double.parseDouble(properties.getProperty("star_velocity_y"));
        int starSize = Integer.parseInt(properties.getProperty("star_size"));
        CelestialObject star = new CelestialObject(starX, starY, starVX, starVY, starSize, Color.RED);
        this.celestialObject.add(star);

        //Store comet properties
        this.genX = Double.parseDouble(properties.getProperty("gen_x"));
        this.genY = Double.parseDouble(properties.getProperty("gen_y"));
        this.bodySize = Integer.parseInt(properties.getProperty("body_size"));
        this.bodyVelocityRange = Integer.parseInt(properties.getProperty("body_velocity"));

        animationTimer = new Timer(timerDelay, this);
    }

    /**
     * Draw celestial objects on the screen.
     * @param g the Graphics provided by Swing for drawing
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Loop through and draw every object in the list
        for (int i = 0; i < this.celestialObject.size(); i++) {
            CelestialObject objectToDraw = this.celestialObject.get(i);
            g.setColor(objectToDraw.color);
            g.fillOval((int) objectToDraw.x, (int) objectToDraw.y, objectToDraw.size, objectToDraw.size);
        }
        animationTimer.start();
    }

    /**
     * Main animation loop called by Timer.
     * This method moves all objects, removes off-screen objects,
     * and generates new comets
     * @param actionEvent the event to be processed from Timer
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //Move objects in the list
        for (int i = 0; i < this.celestialObject.size(); i++) {
            CelestialObject currentObject = this.celestialObject.get(i);
            currentObject.x += currentObject.vx;
            currentObject.y += currentObject.vy;
        }

        //Remove off-screen objects
        for (int i = this.celestialObject.size() - 1; i >= 0; i--) {
            CelestialObject objectToCheck = this.celestialObject.get(i);
            boolean isOffScreen = objectToCheck.x < -objectToCheck.size || objectToCheck.x > this.windowWidth + objectToCheck.size || objectToCheck.y < -objectToCheck.size || objectToCheck.y > this.windowHeight + objectToCheck.size;
            if (isOffScreen) {
                this.celestialObject.remove(i);
            }
        }

        //Generate new comets
        if (cometGenerator.nextDouble() < this.genX) {
            addComet("x-axis");
        }
        if (cometGenerator.nextDouble() < this.genY) {
            addComet("y-axis");
        }
        repaint(); //Redraw
    }

    /**
     * Private helper method to create a new comet at a random edge.
     * The comet is given a random velocity and added the list
     * @param axis determines the spawn edge
     */
    private void addComet(String axis) {
        //Temp variables
        double startX = 0;
        double startY = 0;
        double velocityX = 0;
        double velocityY = 0;

        //Generate a random velocity in the range
        while (velocityX == 0) {
            velocityX = cometGenerator.nextInt(this.bodyVelocityRange * 2 + 1) - this.bodyVelocityRange;
        }
        while (velocityY == 0) {
            velocityY = cometGenerator.nextInt(this.bodyVelocityRange * 2 + 1) - this.bodyVelocityRange;
        }

        if (axis.equals("x-axis")) { //Spawn along the x-axis
            startX = cometGenerator.nextInt(this.windowWidth);
            startY = cometGenerator.nextBoolean() ? 0 : this.windowHeight;
        } else { //Spawn along the y-axis
            startY = cometGenerator.nextInt(this.windowHeight);
            startX = cometGenerator.nextBoolean() ? 0 : this.windowWidth;
        }

        CelestialObject comet = new CelestialObject(startX, startY, velocityX, velocityY, this.bodySize, Color.BLACK);
        this.celestialObject.add(comet);
    }

    /**
     * Main method that sets up the JFrame window and starts the MassiveMotion panel
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java MassiveMotion <property_file_name>");
            System.exit(1);
        }

        System.out.println("Massive Motion starting...");
        MassiveMotion massiveMotion = new MassiveMotion(args[0]);

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(massiveMotion.windowWidth, massiveMotion.windowHeight);
        jf.add(massiveMotion);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
