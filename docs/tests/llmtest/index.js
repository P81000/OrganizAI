import dotenv from 'dotenv';
dotenv.config();
import Replicate from "replicate";
import fs from 'fs';

async function runModel() {
  const replicate = new Replicate({
    auth: process.env.REPLICATE_API_KEY,
  });

  const output = await replicate.run(
    "meta/llama-2-70b-chat:02e509c789964a7ea8736978a43525956ef40397be9033abf9fd2badfe68c9e3",
    {
      input: {
        debug: false,
        top_k: 50,
        top_p: 1,
        //exemplo de prompt solicitando o conteúdo de um JSON para a requisição POST da criação de um novo evento, levando em consideração datas e horas que estão ocupadas
        prompt: "return a .json according to the template below, this .json is from a POST to create an event, dont respond anything besides the .json data:\ncreate an event \"go to the dentist\" and suggest start_date and end_date\nPOST example:\n{\n\"titulo\": \"Team Meeting\",\n\"description\": \"Discussion about quarterly goals\",\n\"start_date\": \"2023-09-20T10:00:00\",\n\"end_date\": \"2023-09-20T12:00:00\",\n\"location\": \"Meeting Room 1\",\n\"event_status\": \"Scheduled\",\n\"user_id\": \"123\",\n\"notification_id\": \"456\",\n\"id_info_clima\": \"789\",\n\"id_info_trajeto\": \"101\"\n}\nDon't use any of the following Dates:\n2023-11-07T10:00:00\n2023-11-08T10:00:00\n2023-11-09T10:00:00\n2023-11-10T10:00:00\n2023-11-11T10:00:00\n2023-11-12T10:00:00\n2023-11-13T10:00:00",
        temperature: 0.5,
        //limite de tokens para a resposta, cada palavra tem cerca de 3 a 4 tokens
        max_new_tokens: 1000,
        min_new_tokens: -1
      }
    }
  );

  // Junta o array em uma única string
  const singleLineString = output.join('');

// Serializa a string para JSON sem espaços extras
  const jsonString = JSON.stringify(singleLineString);

  // Escrevendo para um arquivo chamado 'output.json'
  fs.writeFile('output.json', jsonString, (err) => {
    if (err) {
      console.error('Ocorreu um erro ao escrever o arquivo', err);
    } else {
      console.log('Saída salva em output.json');
    }
  });
}

runModel();
