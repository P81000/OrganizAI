// import dotenv from 'dotenv';
// import Replicate from "replicate";

// dotenv.config();

// class AssistantService {

//     async runModel(msg) {
//         const replicate = new Replicate({
//             auth: process.env.REPLICATE_API_KEY,
//         });
//         return await replicate.run(
//             "meta/llama-2-70b-chat:02e509c789964a7ea8736978a43525956ef40397be9033abf9fd2badfe68c9e3",
//             {
//                 input: {
//                     debug: false,
//                     top_k: 50,
//                     top_p: 1,
//                     prompt: msg,
//                     temperature: 0.5,
//                     //limite de tokens para a resposta, cada palavra tem cerca de 3 a 4 tokens
//                     max_new_tokens: 512,
//                     min_new_tokens: -1
//                 }
//             }
//         );
//     }
// }

// export default new AssistantService();