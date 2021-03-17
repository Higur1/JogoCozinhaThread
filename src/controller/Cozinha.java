package controller;
import java.util.concurrent.Semaphore;

public class Cozinha extends Thread {
	
	private int prato;
	private Semaphore semaforo;

	public Cozinha(int prato, Semaphore semaforo) {
		this.prato = prato;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		if (prato % 2 == 1) {
			SopaDeCebola();
			Entrega();
		}
		if (prato % 2 == 0) {
			LasanhaBolonhesa();
			Entrega();
		}
	}
	private void SopaDeCebola() {
		int FinalizaPrato = 100;
		int tempo = (int)((Math.random()*301)+500);
		while(FinalizaPrato <= tempo) {
			System.out.println("O prato #"+ prato +": Sopa de Cebola cozinhou " + (int)((FinalizaPrato*100)/tempo) + "%");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			FinalizaPrato += 100;
		}
		System.out.println("O prato #" + prato + ": Sopa de Cebola cozinhou 100%");
	}
	private void LasanhaBolonhesa() {
		int FinalizaPrato = 100;
		int tempo = (int) ((Math.random() * 601) + 600);
		while(FinalizaPrato <= tempo) {
			System.out.println("O prato #" + prato + ": Lasanha Bolonhesa cozinhou " + (int)((FinalizaPrato*100)/tempo) + "%");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			FinalizaPrato += 100;
		}
		System.out.println("O prato #" + prato + ": Lasanha Bolonhesa cozinhou 100%");
	}
	private void Entrega() {
		
		String name = "";		
		if(prato % 2 == 0) {
			name = "Sopa de Cebola";
		}
		else {
			if (prato % 2 == 1){
				name = "Lasanha Bolonhesa";
			}
		}	
//----------------Início da seção crítica--------------------
		try {
			semaforo.acquire();
			System.out.println("O prato #" + prato + ": " + name + " está sendo entregue");
			sleep(500);
			System.out.println("O prato #" + prato+ ": "+ name + " foi entregue");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
//----------------- Fim da seção crítica-------------------
	}
}
