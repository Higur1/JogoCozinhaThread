package view;

import java.util.concurrent.Semaphore;

import controller.Cozinha;

public class Servico {

	public static void main(String[] args) {
	int permissoes = 1;
		
		Semaphore semaforo = new Semaphore(permissoes);
		for(int prato = 1; prato <= 5; prato++) {
			Thread StartCozinha = new Cozinha(prato, semaforo);
			StartCozinha.start();
		}
	}
}
