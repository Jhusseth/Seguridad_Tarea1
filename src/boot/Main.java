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
				else if(type==2) {
					
				}
			}
			else if(alg==2) {
				
				System.out.println("Que vas a realizar con AES:");
				System.out.println("1 Encriptar");
				System.out.println("2 Desencriptar");
				
				System.out.print("Opcion: ");
				int type = Integer.parseInt(read.readLine());
				
				if(type==1) {
				
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
				
				else if(type==2) {
					
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
