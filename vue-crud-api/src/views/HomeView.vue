<template>
    <div class="container mt-5 text-center">
      <a href="javascript:void(0)" class="btn btn-lg btn-primary" id="logoutBtn">Logout</a>
      <br>
      <br>
      <br>
      <br>
      <h5>{{ message }}</h5>
    
    </div>
  </template>
  
  <script>
  import {onMounted, ref} from "vue";
  import axios from "axios";
  import {useRouter} from "vue-router";
  
  export default {
    name: "Home",
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
          console.log(data);
          message.value = `Hi ${data}`;
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
            message.value = `You are logout please login again`;
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