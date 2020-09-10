package boot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import aes.AESUtil;
import des.DESUtil;

public class Main {
	
	private static DESUtil des;
	private static AESUtil aes;
	
	
	public static ArrayList<String[]> vectoresDePruebaEncAES() {
		
		ArrayList<String[]> vectores = new ArrayList<>();
		
		String[] uno = {"00000000000000000000000000000000","00000000000000000000000000000000","6a118a874519e64e9963798a503f1d35","dc43be40be0e53712f7e2bf5ca707209"};
		String[] dos = {"ffffffe0000000000000000000000000","00000000000000000000000000000000","00000000000000000000000000000000","18c1b6e2157122056d0243d8a165cddb"};
		String[] tres = {"fffffffffffffffffffffffff8000000","00000000000000000000000000000000","00000000000000000000000000000000","829c04ff4c07513c0b3ef05c03e337b5"};
		String[] cuatro = {"ffffffffffffffffffffffffffffffff","00000000000000000000000000000000","00000000000000000000000000000000","a1f6258c877d5fcd8964484538bfc92c"};
		
		vectores.add(uno);
		vectores.add(dos);
		vectores.add(tres);
		vectores.add(cuatro);
		
		return vectores;
	}
	
	public static ArrayList<String[]> vectoresDePruebaEncDES() {
		
		ArrayList<String[]> vectores = new ArrayList<>();
		
		String[] uno = {"0101010101010101","8000000000000000","95F8A5E5DD31D900"};
		String[] dos = {"0101010101010101","0000000040000000","DF98C8276F54B04B"};
		String[] tres = {"0101010101010101","0000000001000000","4D49DB1532919C9F"};
		String[] cuatro = {"0101010101010101","0000000000000100","DD7C0BBD61FAFD54"};
		
		vectores.add(uno);
		vectores.add(dos);
		vectores.add(tres);
		vectores.add(cuatro);
		
		return vectores;
	}

	public static void main(String[] args) {
		
		BufferedReader read =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		des = new DESUtil();
		aes = new AESUtil();
		
		
		try {
			System.out.println("Que algoritmo va utilizar:");
			System.out.println("1 DES");
			System.out.println("2 AES");
			
			System.out.print("Algoritmo: ");
			int alg = Integer.parseInt(read.readLine());
			
			List<String[]> cadenas = new ArrayList<>();
			
			if(alg==1) {
				
				System.out.println("Que vas a realizar con DES:");
				System.out.println("1 Encriptar");
				System.out.println("2 Desencriptar");
				
				System.out.print("Opcion: ");
				int type = Integer.parseInt(read.readLine());
				
				if(type==1) {
					
					System.out.println("Modo:");
					System.out.println("1 Manual");
					System.out.println("2 Automatico");
					
					System.out.print("Opcion: ");
					int mode = Integer.parseInt(read.readLine());
					
					if(mode==2) {
						
						ArrayList<String[]> test = vectoresDePruebaEncDES();
						
						for(int i =0;i<test.size();i++) {
							
							String enc = des.solve(test.get(i)[0],test.get(i)[1]);
							String answer = test.get(i)[2];
							
							write.write(enc + "ExpectedCipher: "+ answer);
							write.write("\n");
							write.write("--------------------------------------------------------------------------------");
							write.write("\n");
							
						}
					}
					else if(mode==1) {
						System.out.print("Ingrese la cantidad de vectores: ");
						int cvt = Integer.parseInt(read.readLine());
						int i = 0;
						System.out.println("Ingrese el vector clave y el vector mensaje separados con una coma: ");
						System.out.println("Ej: 0101010101010101,8000000000000000");
						System.out.println("Vectores: ");
						do {
							System.out.print((i+1) +" : ");
							String[] vts = read.readLine().toString().split(",");
							cadenas.add(vts);		
							i++;
						}
						while(i<cvt);
						
						System.out.println("--------------------------------------------------------------------------------");
						System.out.println("---------------------------- Values --------------------------------------------");
						
						for(i=0;i<cadenas.size();i++) {
							String answerc = des.solve(cadenas.get(i)[0],cadenas.get(i)[1]);
							
							write.write(answerc + "\n");
							write.write("\n");
						}
					}
				}
				else if(type==2) {
					des.decrypt();
				}
			}
			else if(alg==2) {
				
				System.out.println("Que vas a realizar con AES:");
				System.out.println("1 Encriptar");
				System.out.println("2 Desencriptar");
				
				System.out.print("Opcion: ");
				int type = Integer.parseInt(read.readLine());
				
				if(type==1) {
					
					System.out.println("Modo:");
					System.out.println("1 Manual");
					System.out.println("2 Automatico");
					
					System.out.print("Opcion: ");
					int mode = Integer.parseInt(read.readLine());
					
					if(mode==2) {
						
						ArrayList<String[]> test = vectoresDePruebaEncAES();
						
						for(int i =0;i<test.size();i++) {
							
							String enc = aes.encrypt(test.get(i)[0],test.get(i)[1],test.get(i)[2]);
							String answer = test.get(i)[3];
							
							write.write(enc + "ExpectedCipher: "+ answer);
							write.write("\n");
							write.write("--------------------------------------------------------------------------------");
							write.write("\n");
							
						}
					}
				
					else if(mode==1) {
						System.out.print("Ingrese la cantidad de vectores: ");
					
						int cvt = Integer.parseInt(read.readLine());
						int i = 0;
						System.out.println("Ingrese el vector clave y el vector iv y el vector mensaja separados con una coma: ");
						System.out.println("Ej: 9dc2c84a37850c11699818605f47958c,256953b2feab2a04ae0180d8335bbed6,2e586692e647f5028ec6fa47a55a2aab");
						System.out.println("Vectores: ");
						do {
							System.out.print((i+1) +" : ");
							String[] vts = read.readLine().toString().split(",");
							cadenas.add(vts);		
							i++;
						}
						while(i<cvt);
						
						System.out.println("--------------------------------------------------------------------------------");
						System.out.println("---------------------------- Values --------------------------------------------");
						
						for(i=0;i<cadenas.size();i++) {
							String answerc = aes.encrypt(cadenas.get(i)[0],cadenas.get(i)[1],cadenas.get(i)[2]);
							
							write.write(answerc + "\n");
							write.write("\n");
							
						}
					}
				}
				
				else if(type==2) {
					System.out.println("proximamente ......");
//					aes.decrypt("00000000000000000000000000000000","00000000000000000000000000000000","dc43be40be0e53712f7e2bf5ca707209");
				}
			}
			else {
				return;
			}
			
			write.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
