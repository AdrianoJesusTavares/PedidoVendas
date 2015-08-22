package gyn.jesus.testes;

import java.math.BigDecimal;

public class TesteBigDecimal {

	public static void main(String[] args) {

			BigDecimal custo = new BigDecimal(100);
			BigDecimal percentual = new BigDecimal(10);
			BigDecimal valorVenda = custo;
			
			valorVenda = valorVenda.add(custo.multiply(percentual).divide(new BigDecimal(100)));
			//valorVenda = valorVenda.add(custo);
			System.out.println(valorVenda);
	}

}
