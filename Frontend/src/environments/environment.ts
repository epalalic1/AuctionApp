export const environment = {
    production: false,
    firebaseConfig: {
      apiKey: process.env.FIREBASE_APIKEY,
      authDomain: process.env.FIREBASE_AUTHDOMAIN,
      projectId: process.env.FIREBASE_PROJECTID,
      storageBucket: process.env.FIREBASE_STORAGEBUCKET,
      messagingSenderId: process.env.FIREBASE_MESSAGINGSENDERID,
      appId: process.env.FIREBASE_APPID,
      measurementId: process.env.FIREBASE_MEASUREMENTID
    },
  stripe:{
      api_key: process.env.STRIPE_AK
  },
  localStorageSecurity:{
    key:process.env.LOCALSTORAGEKEY
  }
  };