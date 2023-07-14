<template>

    <div class="container mt-5 text-center">
        <div class="message-container">
          <h5>{{ message }}</h5>
        </div>
    </div>
        
    <div class="container mt-5 text-center">
      <a href="javascript:void(0)" class="btn btn-lg btn-primary" id="logoutBtn">Logout</a>
      <br>

    </div>


    <br>
    <br>

    
    <div class="button-container" >
    <button class="button" @mouseover="toggleHoverEffect(1)" @mouseout="toggleHoverEffect(1)" @click="navigateToItems">
      <img :src="buttonImages[0]" alt="Button 1">
    </button>
    <button class="button" @mouseover="toggleHoverEffect(2)" @mouseout="toggleHoverEffect(2)">
      <img :src="buttonImages[1]" alt="Button 2">
    </button>
  </div>


  </template>
  
  <script>
  import {onMounted, ref} from "vue";
  import axios from "axios";
  import {useRouter} from "vue-router";
  import VueCookies from 'vue-cookies';

  export default {
    name: "Home",
    data() {
    return {
      buttonImages: ['Config.png', 'Monitoring.png'],  // Replace with the paths to your button images
      hoverEffects: [false, false]  // Track hover effects for each button
    };
  },
  methods: {
    navigateToItems() {
      this.$router.push("/Items"); // Replace "/Items" with the desired route path
    },
    toggleHoverEffect(index) {
      this.hoverEffects[index - 1] = !this.hoverEffects[index - 1];
    }
  },


    setup() {
      const message = ref('');
      const router = useRouter();
  
      /*
      onMounted(async () => {
        try {
          const {data} = await axios.get('user');
  
          message.value = `Hi ${data.name}`;
        } catch (e) {
          await router.push('/login');
        }
      });
  
      */
  
      
      onMounted(async () => {
        const sessionCookie = document.cookie.replace(
          /(?:(?:^|.*;\s*)access_Token\s*\=\s*([^;]*).*$)|^.*$/,
          '$1'
        );
        console.log(sessionCookie);
        const logoutBtn = document.getElementById('logoutBtn');
  
  
        //
        if(sessionCookie != ""){
          
          console.log("logout button event attached");
          logoutBtn.addEventListener('click', logout);
        } else{
          logoutBtn.textContent = "login";
          logoutBtn.addEventListener('click', login);
          //message.value = `Please login your account`;

        }
        
        //headers.append('Access-Control-Allow-Origin', 'http://localhost:3000');
        //headers.append('Access-Control-Allow-Credentials', 'true');
        try {
          const response = await axios.get('http://localhost:8087/dashboard', {
            headers: {
              Authorization: `Bearer ${sessionCookie}`,
              'Access-Control-Allow-Origin': '*'
            },
          });
  
          const { data } = response;


          //console.log(data);
          //

      
          // Access the object properties
          const username = data.username;
          const blob = data.blob;

          console.log("Username:", username);
          //console.log("Blob:",  atob(blob));
          VueCookies.set('blob_data',  blob, '7d'); // Save the access token in a cookie for 7 days

          const blobCookie = document.cookie.replace(
            /(?:(?:^|.*;\s*)blob_data\s*\=\s*([^;]*).*$)|^.*$/,
            '$1'
          );
        //console.log(blobCookie);
        //
        // URL decoding
        //var decodedValue = decodeURIComponent(blobCookie);

        // Base64 decoding
        //var base64DecodedValue = atob(decodedValue);
        //console.log(base64DecodedValue);
          //
          message.value = `Username : ${data.username}`;
          if(data.username === undefined){
            message.value = "Please login your account";

          }
        } catch (error) {
          console.error(error);
          await router.push('/');
        }
      });
      
  
          function convertToLogin() {
        logoutBtn.textContent = 'Login';
        logoutBtn.removeEventListener('click', logout);
        logoutBtn.addEventListener('click', login);
      }
            function login() {
          // Perform login actions here
          console.log('Login clicked');
          router.push('/login');
        }
  
  
  
      const logout = async () => {
        const sessionCookie = document.cookie.replace(
          /(?:(?:^|.*;\s*)access_Token\s*\=\s*([^;]*).*$)|^.*$/,
          '$1'
        );
        console.log(sessionCookie);
  
        //
  
        axios.post('http://localhost:8086/api/v1/auth/quit')
          .then(() => {
            // Handle successful logout
            // Clear any user-related data in your Vue.js application
            // Redirect the user to the login page or any other desired route
            console.log("success");
            sessionStorage.removeItem('access_Token');
            document.cookie = "access_Token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            localStorage.removeItem('access_Token');

            message.value = `You are logout please login again`;
            //

            //
            convertToLogin();
  
          })
          .catch(error => {
            // Handle logout error
            console.error(error);
            router.push('/login');
          });
      }
  
      return {
        message,
        logout
      }
  
  
  
    }
  }
  </script>


<style>
.button-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 200px;
  height: 500px;
  border: none;
  background-color: transparent;
  cursor: pointer;
  transition: transform 0.2s;
}

.button img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.button:hover {
  transform: scale(1.1);
}

/* Add additional styling and hover effects as desired */
</style>

