package lbw.arrow;

import java.util.concurrent.ExecutorService;

public class Bow {

	static ExecutorService pool;

	static int count = 0;

	public static void main(String[] args) {

		pool = Prepare.genPool();

		for (int i = 0; i < Config.poolSize; i++) {
			ArchThread archThread = new ArchThread();
			pool.execute(archThread);
		}
	}

}
