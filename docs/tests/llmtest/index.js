import dotenv from 'dotenv';
import Replicate from "replicate";
import fs from 'fs';

dotenv.config();

async function runModel() {
  const replicate = new Replicate({
    auth: process.env.REPLICATE_API_KEY,
  });

  const taskSelec = JSON.parse(fs.readFileSync('./path/to/task.json', 'utf8'));
  const Outrastasks = JSON.parse(fs.readFileSync('./path/to/TODAStasks.json', 'utf8'));

  function generatePrompt(taskSelec) {
    let prompt = taskSelec + "Por favor, escolha uma nova data e horário para a realização da task. Os horarios ocupados são: ";
    
    Outrastasks.forEach((Outrastasks, index) => {
      prompt += `${Outrastasks.date} ${Outrastasks.startTime} - ${Outrastasks.endTime}`;
      
      // Adiciona uma vírgula após cada tarefa, exceto a última
      if (index < Outrastasks.length - 1) {
        prompt += ", ";
      }
    });
  
    return prompt;
  }
  
  // Exemplo de uso
  Outrastasks = [
    {date: "14/10/2021", startTime: "13:00", endTime: "17:00"},
    {date: "15/10/2021", startTime: "08:00", endTime: "12:00"}
  ];
  
  const prompte = generatePrompt(taskSelec);
  
  if(task == incomplete){
  const output = await replicate.run(
    "meta/llama-2-70b-chat:02e509c789964a7ea8736978a43525956ef40397be9033abf9fd2badfe68c9e3",    
    {
      input: {
        debug: false,
        top_k: 50,
        top_p: 1,
        //exemplo de prompt solicitando o conteúdo de um JSON para a requisição POST da criação de um novo evento, levando em consideração datas e horas que estão ocupadas
        prompt: "A seguinte task não foi concluida e precisa ser reagendada" + prompte,
        system_prompt: "Responda sempre em portugues-BR. Você é um assistente prestável, respeitador e honesto. Responde sempre da forma mais útil possível, mas com segurança. As suas respostas não devem incluir conteúdos prejudiciais, pouco éticos, racistas, sexistas, tóxicos, perigosos ou ilegais. Certifique-se de que as suas respostas são socialmente imparciais e de natureza positiva. Deve procurar sempre a melhor hora para o utilizador e nunca sugerir uma hora que seja inconveniente para ele. Nunca escolha uma hora que já esteja reservada.",
        temperature: 0.5,
        //limite de tokens para a resposta, cada palavra tem cerca de 3 a 4 tokens
        max_new_tokens: 512,
        min_new_tokens: -1
      }
    }
  );
  } else if (task == reschedule){
    const output = await replicate.run(
      "meta/llama-2-70b-chat:02e509c789964a7ea8736978a43525956ef40397be9033abf9fd2badfe68c9e3",    
      {
        input: {
          debug: false,
          top_k: 50,
          top_p: 1,
          //exemplo de prompt solicitando o conteúdo de um JSON para a requisição POST da criação de um novo evento, levando em consideração datas e horas que estão ocupadas
          prompt: "A seguinte task precisa ter seu horario modificado" + prompte,
          system_prompt: "Responda sempre em portugues-BR. Você é um assistente prestável, respeitador e honesto. Responde sempre da forma mais útil possível, mas com segurança. As suas respostas não devem incluir conteúdos prejudiciais, pouco éticos, racistas, sexistas, tóxicos, perigosos ou ilegais. Certifique-se de que as suas respostas são socialmente imparciais e de natureza positiva. Deve procurar sempre a melhor hora para o utilizador e nunca sugerir uma hora que seja inconveniente para ele. Nunca escolha uma hora que já esteja reservada.",
          temperature: 0.5,
          //limite de tokens para a resposta, cada palavra tem cerca de 3 a 4 tokens
          max_new_tokens: 512,
          min_new_tokens: -1
        }
      }
    );
  }

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
