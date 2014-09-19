package scenegraph.basics;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class Input {


		//für Standard keyboard
		public static final int NUM_KEYCODES = 256;

		private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
		//jede Taste, welche in einem Frame gedrückt ist
		private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
		private static ArrayList<Integer> upKeys = new ArrayList<Integer>();

		/**
		 * Wird zu jedem Frame aufgerufen
		 */
		public static void update(){
			
			upKeys.clear();
			
			for (int i = 0; i < NUM_KEYCODES; i++) {
				//Wenn die Taste nicht gedrückt ist und sie ist aber in der Liste enthalten,
				//ist sie im aktuellen Frame losgelassen worden
				if (!getKey(i) && currentKeys.contains(i)) {
					upKeys.add(i);
				}
			}
			

			//dass die Liste in jedem Frame wieder leer ist
			downKeys.clear();

			for (int i = 0; i < NUM_KEYCODES; i++) {
				//wenn die Taste gedrückt ist 
				//und sie nicht im vorherigen Frame gedrückt war,
				//dann ist diese Taste erst in diesem Frame gedrückt worden
				if (getKey(i) && !currentKeys.contains(i)) {
					downKeys.add(i);
				}
			}

			currentKeys.clear();

			for (int i = 0; i < NUM_KEYCODES; i++) {
				if (getKey(i)) {
					currentKeys.add(i);
				}
			}
		}

		/**
		 * 
		 * @param keyCode
		 * @return gibt die Code-Zahl für eine Taste auf dem Keyboard zurück
		 */
		public static boolean getKey(int keyCode){

			return Keyboard.isKeyDown(keyCode);
		}

		/**
		 * soll true sein wenn eine Taste gedrückt wird, aber nur für einen Frame
		 * @return true wenn eine Taste gedrückt wird, allerdings nur  einen Frame lang
		 */
		public static boolean getKeyDown(int keyCode){

			if (downKeys.contains(keyCode)) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * Wenn eine Taste losgelassen wird
		 * @param keyCode
		 * @return
		 */
		public static boolean getKeyUp(int keyCode){
			
			if (upKeys.contains(keyCode)) {
				return true;
			} else {
				return false;
			}
		}
	}

