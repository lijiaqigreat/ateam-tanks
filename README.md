Tank Battles!
=============

A yet-to-be-officially-named 2D multiplayer tank combat game which could feature
AI enemies,
music and sound effects,
network play,
explosion physics,
and anything else we come up with!

This game will be a unique blend of turn-based and real-time gameplay elements.

We are writing the game as a desktop application in Java.

--

*Written as a Spring 2014 SLU Software Engineering project by:*

* Nick Lewchenko

* Jiaqi Li

* Andres Wiltz

* barnett

* Imaoye Ekpelu

------------------------------------------------------------------------------------
Directory src Files
------------------------------------------------------------------------------------
1) DemoPanel.java
2) Direction.java
3) DummyUI.java
4) Game.java
5) GameDemo1.java
6) GamePanel.java
7) GameTest.java
8) HitBox.java
9) HumanPlayer.java
10) InterfaceWithGame.java
11) MoveOrder.java
12) Obstacle.java
13) Order.java
14) OrderQueue.java
15) Player.java
16) Projectile.java
17) ReadsMaps.java
18) ShootOrder.java
19) SimpleBullet.java
20) SimpleBulletExplosion.java
21) SimpleTank.java
22) Sprite.java
23) SpriteList.java
24) SpriteListTest.java
25) TurnOrder.java
26) Vector3D.java
27) WaitOrder.java
------------------------------------------------------------------------------------
1) DemoPanel.java
------------------------------------------------------------------------------------
Extends JPanel

Implements InterfaceWithGame, KeyListener

Fields:
	int state
		if 0: display
		if 1: give order
		if 2: announce winner
	SpriteList sprites
	Rectangle2D.Double gameViewRect
	Game game
	SimpleTank mainTank
	int frameLimit
	OrderQueue orderQueue
	String winnerName
	
Functions:
	boolean initializeDisplay(SpriteList sprites, int mapSize)
	void cleanUpAndDestroyDisplay()
	OrderQueue askForOrders(String playerName, int frameLimit, SimpleTank tank)
	void announceWinner(String winnerName)
	void updateTransform(Graphics2D g2)
	void paint(Graphics g)
	void keyTyped(KeyEvent e)
	void keyPressed(KeyEvent e)
	void keyReleased(KeyEvent e)
------------------------------------------------------------------------------------
2) Direction.java
------------------------------------------------------------------------------------
A class to abstract sprite directions

Fields:
	double theta
	double phi

Constructors:
	Direction(double dir)
	Direction(double dir, double phi)
	Direction(Direction other)
	Direction(Direction other, Direction offset)

Functions:
	double getTheta()
		returns double theta
	double getPhi()
		return double phi
	String toString()
		returns String of Direction
	double round(double dir)
		private static
		returns double rounded dir
	double bound(double dir)
		private static
		returns bounded double dir
------------------------------------------------------------------------------------
3) DummyUI.java
------------------------------------------------------------------------------------
implements InterfaceWithGame

A do-nothing standin for testing the Player and Game classes

Does print position information

Fields:
	SpriteList sprites
	
Constructors:
	DummyUI()
	
Functions:
	boolean initializeDisplay(SpriteList sprites, int mapsize)
	void cleanUpAndDestroyDisplay()
	void updateDisplay()
	OrderQueue askForOrders(String playerName, int frameLimit, SimpleTank sprite)
	void announceWinner(String winnerName)
		prints message for winner
------------------------------------------------------------------------------------
4) Game.java
------------------------------------------------------------------------------------
A game class that holds all relevant data and the game interfacee and runs the main game loop

Idea is to spawn fro a higher up gui-centric MainMenu type class, and run this independantly until the game ends and returns control to that menu

Fields:
	ArrayList<Player> players
	SpriteList sprites
	InterfaceWithGame display
	int framesPerTurn
	int turnLimit
	int mapsize
	Console console
	
Constructors:
	Game(ArrayList<Player> p, SpriteList s, InterfaceWithGame d, int frames, int turns, int mapsize)
	
Functions:
	int run()
		initialize display
		runs the game
------------------------------------------------------------------------------------
5) GameDemo1.java
------------------------------------------------------------------------------------
A core test for the Game/Player structure, as well as the dummy ui

Acting in place of the main gui menu that will eventually be written to launch games

Similar to GameTest.java

Functions:
	static void main(String args[])
	static void test1()
	static void test2()
	static void debug(String s)
------------------------------------------------------------------------------------
6) GamePanel.java
------------------------------------------------------------------------------------
Extends JPanel

Implements InterfaceWithGame, KeyListener

Fields:
	int state
		if 0: display
		if 1: give order
		if 2: announce winner
	SpriteList sprites
	Rectangle2D.Double gameViewRect
	Game game
	SimpleTank mainTank
	int frameLimit
	OrderQueue orderQueue
	String winnerName

Functions:
	boolean initializeDisplay(SpriteList sprites, int mapSize)
	void cleanUpAndDestroyDisplay()
	void updateDisplay()
	OrderQueue askForOrders(String playerName, int frameLimit, SimpleTank tank)
	void announceWinner(String winnerName)
	void updateTransform(Graphics2D g2)
	void paint(Graphics g)
		paints the board
	void keyTyped(KeyEvent e)
	void keyPressed(KeyEvent e)
	void keyReleased(KeyEvent e)
------------------------------------------------------------------------------------
7) GameTest.java
------------------------------------------------------------------------------------
A core test for the Game/Player structure, as well as dummy ui

Test in place of the main gui menu that will eventually be written to launch the games

Functions:
	void main(String args[])
		starts test2()
	void test1()
	void test2()
	void debug(String s)
------------------------------------------------------------------------------------
8) HitBox.java
------------------------------------------------------------------------------------
A rectangular prism in which a sprite resides

If two sprites' HitBoxes intersect, they are considered to be colliding

Fields:
	double length
	double width
	double height

Constructors:
	HitBox()
	HitBox(double l, double w, double h)
	HitBox (HitBox other)

Functions:
	double getLength()
		returns the length of the HitBox
	double getWidth()
		returns the width of the HitBox
	double getHeight()
		returns the height of the HitBox
------------------------------------------------------------------------------------
9) HumanPlayer.java
------------------------------------------------------------------------------------
Extends Player

A player class for a human

Fields:
	InterfaceWithGame display

Constructors:
	HumanPlayer(InterfaceWithGame iwg, String playerName, ArrayList<SimpleTanks> tanks, Color c)
	
Functions:
	void giveOrders(int frameLimit)
------------------------------------------------------------------------------------
10) InterfaceWithGame.java
------------------------------------------------------------------------------------
An interface for a general ui (graphical or not) that the game would use during the order-getting and running phases

Does not include functionality of the main menu
	
Functions:
	boolean initializeDisplay(SpriteList sprites, int mapSize)
		Contains any initialization code such as opening a window
	void cleanUpAndDestroyDisplay()
		Contains code needed for closing windows or cleaning up other interface elements
	void updateDisplay()
		Updates display with current positions and states of sprites
	OrderQueue askForOrders(String playerName, int frameLimit, Simpletank tank)
		Performs arbitrary actions to prompt a human player for orders for a particular unit and gets those orders
	void announceWinner(String winnerName)
		Performs some display to congratulate the winning player
------------------------------------------------------------------------------------
11) MoveOrder.java
------------------------------------------------------------------------------------
Extends Order

An order class for moving forward or backwards

Fields:
	int direction
		if 1 move forward
		if -1 move backward

Constructors:
	MoveOrder(int frames, int direction)
	
Functions:
	int getDirection()
		returns direction
	void execSpecific(SimpleTank tank)
------------------------------------------------------------------------------------
12) Obtsacle.java
------------------------------------------------------------------------------------
Extends Sprite

A sprite representing a non-moving object on the playing field

Constructors:
	Obstacle(SpriteList sprites, Vector3D position, Direction direction, HitBox box, color c)
	
Functions:
	int update()
	void damage(int intensity)
		does nothing
	void paint(Graphics2D g)
------------------------------------------------------------------------------------
13) Order.java
------------------------------------------------------------------------------------
An abstract class

A general order

Every order lasts a certain number of frames, and defines how the tank will behave for those frames of the game

Represent one pass of the main game loop

Fields:
	int frames

Constructors:
	Order(int frames)

Functions:
	void exec(SimpleTank tank)
	void execSpecific(SimpleTank tank)
		protected abstract
	int getFrames()
		returns number of frames
	Object clone()
------------------------------------------------------------------------------------
14) OrderQueue.java
------------------------------------------------------------------------------------
A containing class for a list of orders that each tank recieves at the beginning of each turn

It takes care of discarding orders when they are out of frames

Filled during the player's ordering phase before each turn

Fields:
	int framesLeft
	Queue<Order> orders
	
Constructors:
	OrderQueue()
	OrderQueue(int framesAllowed)
	
Functions:
	int getFramesLeft()
		returns number of frames left
	int add(Order o)
		return 0 when successful
		returns 1 when no more frames are left
	int exec(SimpleTank tank)
		return 0 when successful
		return 1 when queue is empty
------------------------------------------------------------------------------------
15) Player.java
------------------------------------------------------------------------------------
A class to represent a player, for the purpose of sprite ownership and orders allocation

Fields:
	String playerName
	Color color
	ArrayList<SimpleTank> owndedTanks
	
Constructors:
	Player( String name, ArrayList<SimpleTank> tanks, Color c)

Functions:
	void giveOrders(int frameLimit)
		gives order to tanks
	boolean stillAlive()
		checks if any tanks left
	String getName()
		returns the name of the player
	Color getColor()
		returns the color of the player
------------------------------------------------------------------------------------
16) Projectile.java
------------------------------------------------------------------------------------
An abstract class that extends Sprite

Fields:
	Vector3D velocity
	Vector3D gravity
	
Constructors:
	Projectile(SpriteList sprites, Vector3D position, Direction direction, HitBox hitbox, Color color, Vector3D velocity, Vector3D gravity)
	
Functions:
	void reactToCollision(ArrayList<Sprite> collisions)
		abstract
		method for exploding or inflicting damage or whatever the projectile is suppose to do upon hitting another sprite
	void damage(int intensity)
		abstract
	int update
------------------------------------------------------------------------------------
17) ReadsMaps.java
------------------------------------------------------------------------------------
An interface that takes the name of a text file and generates a list of sprites from it

Fields:
	ArrayList<Sprite> readMap(String nameOfMapFile)
------------------------------------------------------------------------------------
18) ShootOrder.java
------------------------------------------------------------------------------------
Extends Order

A simple order for shooting SimpleBullets

Fields:
	Direction direction

Constructors:
	ShootOrder(double theta)

Functions:
	void execSpecific(SimpleTank tank)
------------------------------------------------------------------------------------
19) SimpleBullet.java
------------------------------------------------------------------------------------
Extends projectile

Constructors:
	SimpleBullet(SpriteList sprites, Vector3D position, Direction direction)
	
Functions:
	void reactToCollision(ArrayList<Sprite> collision)
	void paint(Graphics2D g)
	void damage(int intensity)
------------------------------------------------------------------------------------
20) SimpleBulletExplosion.java
------------------------------------------------------------------------------------
Extends Sprite

Fields:
	int frames
	Color c

Constructors:
	SimpleBulletExplosion(SpriteList sprites, Vector3D position)
	
Functions:
	int update()
	void damage(int intensity)
	void paint(Graphics2D g)
------------------------------------------------------------------------------------
21) SimpleTank.java
------------------------------------------------------------------------------------
Extends Sprite

A tank class

Fields:
	ArrayList<SimpleTank> playerTanks
	double speed
	double handling
	OrderQueue orders
	int health
	Color color
	
Constructors:
	SimpleTank(SpriteList sprites, ArrayList<SimpleTank> playerTanks, Vector3D position, Direction direction, double speed, double handling, Color c)
	
Functions:
	void giveOrders(OrderQueue newOrders)
	void shoot(Direction direction)
	void damage(int intensity)
	double getSpeed()
		returns speed of tank
	double getHandling()
		returns handling of the tank
	void kill()
	int update()
	void paint(Graphics2D g)
		Paints tank on screen
------------------------------------------------------------------------------------
22) Sprite.java
------------------------------------------------------------------------------------
Abstract class that extends Object

General class for anything that might be placed, removed, or moved or changed in any way on the field during play

Idea is that the main game loop can iterate over a list of these and update each one

Fields:
	SpriteList sprites
	Vector3D position
	Direction direction
	boolean alive
	double hitboxRadius
	
Constructors:
	Sprite(SpriteList sprites, Vector3D p, Direction d, double hr)
	
Functions
	abstract int update()
	abstract void damage(int intensity)
	abstract void paint(Graphics2D g)
	boolean checkCollision(Sprite other)
	Arraylist<Sprite> getAllCollisions()
	Vector3D getPosition()
		returns the position of the sprite
	void setPosition(Vector3D p)
		sets the position of the sprite
	Direction getDirection()
		returns the direction of the sprite
	void setDirection(Direction d)
		sets the direction of the sprite
	void kill()
	static Arc2D.Double getCircle(double x, double y, double radius)
------------------------------------------------------------------------------------
23) SpriteList.java
------------------------------------------------------------------------------------
Class abstracts the workings of the sprite list

Handles postponing list alterations until they are safe to perform

Fields:
	ArrayList<Sprite> sprites
	ArrayList<Sprite> toBeAdded
	ArrayList<Sprite> toBeRemoved
	
Constructors:
	SpriteList()
	SpriteList(SpriteList other)
	SpriteList(ArrayList<Sprite> initial)
	
Functions:
	ArrayList<Sprite> getSprites()
		returns the sprites
	void add(Sprite newSprite)
	void remove(Sprite deadSprite)
	void update()
------------------------------------------------------------------------------------
24) SpriteListTest.java
------------------------------------------------------------------------------------
A test for sprite lists

Functions:
	void main(Strings args[])
------------------------------------------------------------------------------------
25) TurnOrder.java
------------------------------------------------------------------------------------
Extends Order

An order for turning the tank

Fields:
	int direction
	
Constructors:
	TurnOrder(int frames, int direction)
	
Functions:
	int getDirection()
		returns the direction of the order
	void execSpecific(SimpleTank tank)
------------------------------------------------------------------------------------
26) Vector3D.java
------------------------------------------------------------------------------------
A class to represent vectors

Used to represent positions, velocities, and offsets

Fields:
	double x
	double y
	double z
	
Constructors:
	Vector3D()
	Vector3D((double x, double y, double z)
	Vector3D(Vector3D other)
	Vector3D(double magnitude, Direction direction)

Functions:
	void add(Vector3D other)
	double getX()
		returns the x of the vector
	double getY()
		returns the y of the vector
	double getZ()
		returns the z of the vector
------------------------------------------------------------------------------------
27) WaitOrder.java
------------------------------------------------------------------------------------
Extends Order

A very simple order for shooting SimpleBullets

Constructors:
	WaitOrder(int frames)

Functions:
	void execSpecific(SimpleTank tank)
------------------------------------------------------------------------------------
