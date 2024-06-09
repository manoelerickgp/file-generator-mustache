package poc.manoel.fileGenerator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class FileGeneratorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FileGeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Dados para o template 2 (faturas)
		String fatura = "" ;

		// Número de vezes que a fatura deve ser repetida
		int repeatCount = 3;

		// Criar lista de faturas repetidas
		List<Map<String, String>> items = new ArrayList<>();
		for (int i = 0; i < repeatCount; i++) {
			Map<String, String> item = new HashMap<>();
			item.put("fatura", fatura);
			items.add(item);
		}

		// Total de linhas do template 2
		int total = items.size();

		// Dados para o template 1
		Map<String, Object> data = new HashMap<>();
		data.put("items", items);
		data.put("total", total);

		// Processar os templates
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache template1 = mf.compile("template1.mustache");

		// Gerar o arquivo
		try (Writer fileWriter = new FileWriter("output3.txt")) {
			template1.execute(fileWriter, data).flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Arquivo HTML gerado com sucesso.");
	}
}
