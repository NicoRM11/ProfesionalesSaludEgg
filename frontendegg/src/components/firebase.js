
import { initializeApp } from "firebase/app";
import { getStorage } from 'firebase/storage';



const firebaseConfig = {
  apiKey: "AIzaSyAdUN8A46vIB8MzkwmFDz3WOcVgD_tOriI",
  authDomain: "profesionalessaludegg.firebaseapp.com",
  projectId: "profesionalessaludegg",
  storageBucket: "profesionalessaludegg.appspot.com",
  messagingSenderId: "664222042516",
  appId: "1:664222042516:web:e02571ca1cebc5cdfbe781"
};


const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);