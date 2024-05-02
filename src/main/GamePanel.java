package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Collision.Collision;
import Content.Chapter;
import Content.Chapter1;
import Content.Chapter2;
import GUI.MissionDescription;
import Inventory.Inventory;
import Keyboard.KeyboardManager;
import MainMenu.AudioSetting;
import MainMenu.KeySetting;
import MainMenu.LoadGame;
import MainMenu.Main_Menu;
import MainMenu.MouseListener_Mainmenu;
import MainMenu.MouseMotionListener_Mainmenu;
import MainMenu.NextMainMenu;
import MainMenu.PauseGame;
import MainMenu.Setting;
import MainMenu.VideoSetting;
import Mouse.MouseManager;
import area.C2_hall;
import area.C2_hallway;
import area.ComputerRoom;
import area.Library;
import area.MyRoom;
import area.NormalClassroom;
import area.Section_1;
import area.Section_2;
import area.Section_3;
import area.Stadium;
import area.D3_5_hallway.D3_5_hallway_secondfloor;
import entity.Player;
import map.Map;
import phone.Phone;
import section_selection.Section_selection;
import sound.Sound;
import sound.SoundManager;
import tile.TileManager;
import time.TimeSystem;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    public static double scale = 3 * Main.ex;
    public static double screenWidth = 256 * scale;
    public static double screenHeight = 192 * scale;

    // =================================================================================================================
    // MAP SETTINGS

    public TileManager tileManager = new TileManager(this);
    Thread gameThread;
    SoundManager soundManager = new SoundManager();
    TimeSystem timeSystem = new TimeSystem();

    public static Main_Menu mainMenu = new Main_Menu();
    public static NextMainMenu nextMainMenu = new NextMainMenu();
    public static Setting setting = new Setting();
    public static AudioSetting audioSetting = new AudioSetting();
    public static KeySetting keySetting = new KeySetting();
    public static VideoSetting videoSetting = new VideoSetting();
    public static LoadGame loadGame = new LoadGame();
    public static PauseGame pauseGame = new PauseGame();

    MouseListener_Mainmenu mouseListenerMainmenu = new MouseListener_Mainmenu(this);
    MouseMotionListener_Mainmenu mouseMotionListenerMainmenu = new MouseMotionListener_Mainmenu(this, mainMenu,
            nextMainMenu, setting, audioSetting, keySetting, videoSetting, loadGame, pauseGame);
    // Khai báo lớp NormalClassroom vào GamePanel
    public NormalClassroom normalClassroom = new NormalClassroom(this);
    public ComputerRoom computerRoom = new ComputerRoom(this);
    public Library library = new Library(this);
    public Stadium stadium = new Stadium(this);
    public MyRoom myRoom = new MyRoom(this);
    // public Section_3 section_3 = new Section_3(this);

    // =================================================================================================
    public static double next_screenWidth = 256 * scale;
    public static double next_screenHeight = 192 * scale;
    public static double prev_screenWidth = 256 * scale;
    public static double prev_screenHeight = 192 * scale;
    public static double next_scale = 3;
    public static double prev_scale = 3;

    // ==============================================================================================

    public MouseManager mouseManager = new MouseManager(this);
    public Map currentMap = null; // map hien tai
    // Section_2 section_2 = new Section_2(this);
    // Section_1 section_1 = new Section_1(this);

    KeyboardManager keyboardManager = new KeyboardManager();
    public UI ui = new UI(this);
    public Phone phone = new Phone(this);
    public MissionDescription missionDescription = new MissionDescription(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Collision collision = new Collision(this);
    public Player player = new Player(this, keyH, tileManager, ui);
    public Inventory inventory = new Inventory(this);

    public Section_selection section_selection = new Section_selection(this);
    public Section_3 section_3 = new Section_3(this);
    public Section_2 section_2 = new Section_2(this);
    public Section_1 section_1 = new Section_1(this);
    public boolean isRunning = false;
    public C2_hall c2_hall = new C2_hall(this);
    public C2_hallway c2_hallway = new C2_hallway(this);

    // ==========================================================

    public Chapter currentChapter = new Chapter();

    public Chapter1 chapter1 = new Chapter1(this);
    public Chapter2 chapter2 = new Chapter2(this);

    // =========================================================

    double FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension((int) screenWidth, (int) screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.Mouse();

    }

    public void Mouse() {
        this.addMouseMotionListener(mouseMotionListenerMainmenu);
        this.addMouseListener(mouseListenerMainmenu);
        this.addMouseListener(mouseManager);
        this.addKeyListener(keyboardManager);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        isRunning = true;
    }

    public void screenResize() {
        missionDescription.screenResize();
        phone.screenResize();
        if(currentMap == myRoom) {
            myRoom.setDefaultValues();
            myRoom.reSizeMap(this);
        }
        if(currentMap == normalClassroom) {
            normalClassroom.setDefaultValues();
            normalClassroom.reSizeMap(this);
        }
        if(currentMap == computerRoom) {
            computerRoom.setDefaultValues();
            computerRoom.reSizeMap(this);
        }
        if(currentMap == library) {
            library.setDefaultValues();
            library.reSizeMap(this);
        }
        if(currentMap == stadium) {
            stadium.setDefaultValues();
            stadium.reSizeMap(this);
        }
        currentMap.loadMap(this);
        player.setDefaultValues();
        inventory.ScreenResize();
        ui.screenResize();
        section_selection.screenResize();
    }

    public void Init() {
        switch (Main.nguoncode) {
            case 1: {
                newGame();
                break;
            }
            case 2: {
                newGame();
                break;
            }

            case 3: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = section_1;
                break;
            }

            case 4: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = normalClassroom;
                break;
            }
            case 5: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = computerRoom;
                break;
            }
            case 6: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = stadium;
                break;
            }
            case 7: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = library;
                break;
            }
            case 8: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = myRoom;
                break;
            }
            case 9: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = section_3;
                break;
            }
            case 10: {
                if (Main.GameState.empty() || !Main.topGameState().equals("GamePlay"))
                    Main.pushGameState("GamePlay");
                currentMap = section_2;
                break;
            }
        }
        currentChapter = chapter1;
        currentMap.loadMap(this);
        keyboardManager.init();
        keySetting.init();
    }

    private void stopThread() {
        SoundManager.stopAllSound();
    }

    public void run() {
        soundManager.addSound(new Sound("piano_music", "res/sound/pianos-by-jtwayne-7-174717.wav"));
        // SoundManager.loopSound("piano_music");

        soundManager.addSound(new Sound("guitar_music", "res/sound/acoustic-guitar-loop-f-91bpm-132687.wav"));
        // soundManager.loopSound("guitar_music");
        Init();

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            if (!isRunning) {
                stopThread();
                break;
            }

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // =================================================================================================================
    public void update() {
        timeSystem.update();
        soundManager.update();
        tileManager.update();
        currentChapter.update();
        phone.update();
        player.update();
        inventory.update();
        missionDescription.update();
        if (Main.nguoncode == 1) {
            if (Main.topGameState().equals(Main.states[0])) {
                mainMenu.update();
            } else if (Main.topGameState().equals(Main.states[1])) {
                nextMainMenu.update();
            } else if (Main.topGameState().equals(Main.states[2])) {
                setting.update();
            } else if (Main.topGameState().equals(Main.states[3])) {
                audioSetting.update();
            } else if (Main.topGameState().equals(Main.states[4]))
                keySetting.update();
            else if (Main.topGameState().equals(Main.states[5]))
                videoSetting.update();
            else if (Main.topGameState().equals(Main.states[12]))
                loadGame.update();
            else if (Main.topGameState().equals("PauseGame"))
                pauseGame.update();
        } else if (Main.nguoncode == 2) {
            if (Main.topGameState().equals(Main.states[0])) {
                mainMenu.update();
            } else if (Main.topGameState().equals(Main.states[1])) {
                nextMainMenu.update();
            } else if (Main.topGameState().equals(Main.states[2])) {
                setting.update();
            } else if (Main.topGameState().equals(Main.states[3])) {
                audioSetting.update();
            } else if (Main.topGameState().equals(Main.states[4]))
                keySetting.update();
            else if (Main.topGameState().equals(Main.states[5]))
                videoSetting.update();
            else if (Main.topGameState().equals(Main.states[12]))
                loadGame.update();
            else if (Main.topGameState().equals("PauseGame"))
                pauseGame.update();
        }
        if (Main.topGameState().equals("GamePlay")){
            if (keyH.isInteract) {
                if (player.ButtonInteract)
                    collision.update();
                else
                    keyH.isInteract = false;
            }
            if (keyH.isPhonePressed) {
                // System.out.println("phone-kun xin chao tat ca cac ban");
                if (inventory.isExist("Iphone 100 ProMax")) {
                    KeyboardManager.resetReleasedKey();
                    phone.isDrawPhone = !phone.isDrawPhone;
                    phone.setPhoneState("Screen Saver");
                }
                keyH.isPhonePressed = false;
            }
        } else {
            if (keyH.isPhonePressed) {
                keyH.isPhonePressed = false;
            }
        }
    }
    // =================================================================================================================

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // RenderingHints rh1 = new RenderingHints(
        // RenderingHints.KEY_RENDERING,
        // RenderingHints.VALUE_RENDER_SPEED);
        // g2.setRenderingHints(rh1);

        switch (Main.nguoncode) {
            case 1: {
                if (Main.topGameState().equals(Main.states[0]))
                    mainMenu.draw(g2);
                else if (Main.topGameState().equals(Main.states[1]))
                    nextMainMenu.draw(g2);
                else if (Main.topGameState().equals(Main.states[2]))
                    setting.draw(g2);
                else if (Main.topGameState().equals(Main.states[3]))
                    audioSetting.draw(g2);
                else if (Main.topGameState().equals(Main.states[4]))
                    keySetting.draw(g2);
                else if (Main.topGameState().equals(Main.states[5]))
                    videoSetting.draw(g2);
                else if (Main.topGameState().equals(Main.states[12]))
                    loadGame.draw(g2);
                else if (Main.topGameState().equals("PauseGame"))
                    pauseGame.draw(g2);
                break;
            }
            case 2: {
                if (Main.topGameState().equals(Main.states[0])) {
                    mainMenu.draw(g2);
                } else if (Main.topGameState().equals(Main.states[1])) {
                    nextMainMenu.draw(g2);
                } else if (Main.topGameState().equals(Main.states[2]))
                    setting.draw(g2);
                else if (Main.topGameState().equals(Main.states[3]))
                    audioSetting.draw(g2);
                else if (Main.topGameState().equals(Main.states[4]))
                    keySetting.draw(g2);
                else if (Main.topGameState().equals(Main.states[5]))
                    videoSetting.draw(g2);
                else if (Main.topGameState().equals(Main.states[12]))
                    loadGame.draw(g2);
                else if (Main.topGameState().equals("PauseGame"))
                    pauseGame.draw(g2);
                break;
            }
        }

        if (Main.topGameState().equals("GamePlay") || Main.topGameState().equals("Dialog")
                || Main.topGameState().equals("GamePlay")
                || Main.topGameState().equals("Inventory")
                || Main.topGameState().equals("Dialogue")) {
            if (chapter1.IntroFinished) {
                drawMap(g2);
                player.draw(g2);
                inventory.draw(g2);
                missionDescription.draw(g2);
                phone.draw(g2);
            }
            ui.draw(g2);
        }

        if (Main.topGameState().equals("Map")) {
            section_selection.operation(g);
        }

    }

    public void drawMap(Graphics2D g2) {
        if (currentMap == myRoom) {
            myRoom.draw(g2);
        }
        if (currentMap == normalClassroom) {
            normalClassroom.draw(g2);
        }
        if (currentMap == computerRoom) {
            computerRoom.draw(g2);
        }
        if (currentMap == library) {
            library.draw(g2);
        }
        if (currentMap == stadium) {
            stadium.draw(g2);
        }
        if (currentMap == section_1) {
            section_1.draw(g2);
        }
        if (currentMap == section_2) {
            section_2.draw(g2);
        }
        if (currentMap == section_3) {
            section_3.draw(g2);
        }
        if (currentMap == c2_hall) {
            c2_hall.draw(g2);
        }
        if (currentMap == c2_hallway) {
            c2_hallway.draw(g2);
        }
    }

    public void newGame() {
        currentMap = myRoom;
        currentChapter = chapter1;
        chapter1.currentTimeline = 0;
        chapter1.IntroFinished = false;
        chapter1.completedAct = 0;
    }
}
