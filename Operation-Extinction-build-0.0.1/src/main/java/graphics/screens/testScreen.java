package graphics.screens;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.*;

import java.nio.*;
import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;



public class testScreen implements Menu {
	private long window;
	private int shaderProgram;
	private int VBO, VAO;
	private float[] vertices = {
		-0.5f, -0.5f, 0.0f,
		0.5f, -0.5f, 0.0f,
		0.0f, 0.5f, 0.0f
	};
	
	public testScreen() {};
	
	public void run() {
		if(!glfwInit())
			throw new IllegalStateException("glfwInit() failed");
		long win = glfwCreateWindow(640,640,"Window", 0, 0);
		glfwShowWindow(win);
		glfwMakeContextCurrent(win);
		GL.createCapabilities();
		OE_Camera camera = new OE_Camera(640,640);
		glEnable(GL_TEXTURE_2D);
		float[] vertices = new float[] {
				-0.5f, 0.5f, 0, 	// TOP LEFT			0
				0.5f, 0.5f, 0, 		// TOP RIGHT		1
				0.5f, -0.5f, 0, 	// BOTTOM RIGHT		2
				-0.5f, -0.5f, 0, 	// BOTTOM LEFT		3
		};
		float[] textures = new float[] {
				0,0,
				1,0,
				1,1,	
				0,1,
		};
		int[] indices = new int[] {
				0,1,2,				// TOP LEFT, TOP RIGHT, BOTTOM RIGHT (first triangle)
				2,3,0,				// BOTTOM RIGHT, BOTTOM LEFT, TOP LEFT (2nd triangle)
		};
		OE_Model model = new OE_Model(vertices, textures, indices);
		OE_Texture tex = new OE_Texture("./src/main/resources/graphics/cardTest.png");
		// scales and translates the texture
		Matrix4f scale = new Matrix4f()
				.translate(new Vector3f(100,0,0))
				.scale(200);
		Matrix4f target = new Matrix4f();
		
		camera.setPosition(new Vector3f(-100,0,0));
		
		// This will multiply scale (a matrix) with projection, and puts it onto target
		OE_Shader shader = new OE_Shader("test");
		float x = 0;
		while(!glfwWindowShouldClose(win)) {
			target = scale; 
			glfwPollEvents();
			if(glfwGetKey(win, GLFW_KEY_ESCAPE)== GL_TRUE) {
				glfwSetWindowShouldClose(win, true);
			}
			if(glfwGetMouseButton(win, GLFW_MOUSE_BUTTON_1) == GL_TRUE) {
				x += 0.001f;
			}
			glClear(GL_COLOR_BUFFER_BIT);
			shader.bind();
			shader.setUniform("sampler", 0); // enable 0th sampler 
			shader.setUniform("projection", camera.getProjection().mul(target));
			tex.bind(0);
			model.render();
			glfwSwapBuffers(win);
		}
		glfwTerminate();
	}
	
	// OLDER
//	public void run() {
//		init();
//		loop();
//		GL33.glDeleteVertexArrays(VAO);
//		GL33.glDeleteBuffers(VBO);
//		GL33.glDeleteProgram(shaderProgram);
//		glfwFreeCallbacks(window);
//		glfwDestroyWindow(window);
//		glfwTerminate();
//		glfwSetErrorCallback(null).free();
//	}
//	private void init() {
//		// Initial window setup
//		GLFWErrorCallback.createPrint(System.err).set();
//		if(!glfwInit())
//			throw new IllegalStateException("glfwInit() failed");
//		glfwDefaultWindowHints();
//		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
//		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
//		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
//		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
//		window = glfwCreateWindow(300,300,"Hello World!", NULL, NULL);
//		if(window == NULL)
//			throw new RuntimeException("glfwCreateWindow() failed");
//		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
//			if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
//				glfwSetWindowShouldClose(window, true);
//		});
//		glfwMakeContextCurrent(window);
//		try(MemoryStack stack = stackPush()) {
//			IntBuffer pWidth = stack.mallocInt(1);
//			IntBuffer pHeight = stack.mallocInt(1);
//			glfwGetWindowSize(window, pWidth, pHeight);
//			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//			glfwSetWindowPos(
//					window,
//					(vidmode.width() - pWidth.get(0)) / 2,
//					(vidmode.height() - pHeight.get(0)) / 2
//					);
//		}
//		// Vertex Shader
//		int vertexShader = GL20C.glCreateShader(GL33.GL_VERTEX_SHADER);			// <-- error
//		int fragmentShader = GL20C.glCreateShader(GL33.GL_FRAGMENT_SHADER);
//		String vertexShaderSource = "#version 330 core\n"
//				+ "layout (location = 0) in vec3 aPos;\n"
//				+ "void main()\n"
//				+ "{\n"
//				+ "		gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
//				+ "}\0";
//		String fragmentShaderSource = "#version 330 core\n"
//				+ "out vec4 FragColor;\n"
//				+ "void main()\n"
//				+ "{\n"
//				+ "		FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);\n"
//				+ "}\0";
//		// Compiling Shaders
//		GL33.glShaderSource(vertexShader, vertexShaderSource);
//		GL33.glShaderSource(fragmentShader, fragmentShaderSource);
//		GL33.glCompileShader(vertexShader);
//		GL33.glCompileShader(fragmentShader);
//		// Shader Program
//		shaderProgram = GL33.glCreateProgram();
//		GL33.glAttachShader(shaderProgram, vertexShader);
//		GL33.glAttachShader(shaderProgram, fragmentShader);
//		GL33.glLinkProgram(shaderProgram);
//		//GL33.glUseProgram(shaderProgram);
//		//GL33.glBindVertexArray(VAO);
//		GL33.glDeleteShader(vertexShader);
//		GL33.glDeleteShader(fragmentShader);
//		// Vertex Attributes
//		VAO = GL33.glGenVertexArrays();
//		VBO = GL33.glGenBuffers();
//		GL33.glBindVertexArray(VAO);
//		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, VBO);
//		GL33.glBufferData(GL33.GL_ARRAY_BUFFER, vertices, GL33.GL_STATIC_DRAW);
//		ByteBuffer h = ByteBuffer.allocate(36);
//		GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, (3 * 4), h);
//		GL33.glEnableVertexAttribArray(0);
//		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
//		GL33.glBindVertexArray(0);
//		glfwSwapInterval(1);
//		glfwShowWindow(window);
//	}
//	private void loop() {
//		GL.createCapabilities();
//		glClearColor(1.0f,0.0f,0.0f,0.0f);
//		while(!glfwWindowShouldClose(window)) {
//			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//			GL33.glUseProgram(shaderProgram);
//			GL33.glBindVertexArray(VAO);
//			GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, 3);
//			glfwSwapBuffers(window);
//			glfwPollEvents();
//		}
//	}
}
