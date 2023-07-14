<template>
    <main class="form-signin">
      <form @submit.prevent="submit">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
  
        <div class="form-floating">
          <label>Email&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
          <input type="email" class="form-control" name="email" placeholder="name@example.com">
        </div>
        <br>
        <div class="form-floating">
          <label>Password&nbsp</label>
          <input type="password" class="form-control" name="password" placeholder="Password">
        </div>
        <br>
        <br>
        <button class="w-100 btn btn-lg btn-primary blue-ocean" type="submit" >Submit</button>
      </form>
    </main>

    <!----->





  
  </template>
  
  <script>
  import {useRouter} from "vue-router";
  import axios from "axios";
  import VueCookies from 'vue-cookies';

  //

  export default {
    name: "Login",
    data() {
    return {
      email: '',
      password: ''
    };
    },



    
    setup() {
        const router = useRouter();
  
        const submit = async e => {

        //

        //
        try{
        const form = new FormData(e.target);
  
        const inputs = Object.fromEntries(form.entries());
        console.log(inputs);
        const loginData = {
            email: inputs.email,
            password: inputs.password
        };
        const {data} = await axios.post(process.env.VUE_APP_API_URI, loginData);
        console.log(data);
        console.log(data['access_token']);


        localStorage.setItem('access_Token', data['access_token']);
        VueCookies.set('access_Token',  data['access_token'], '7d'); // Save the access token in a cookie for 7 days

        //this.setCookie('access_Token',  data['access_token'], 7);
       // axios.defaults.headers.common['Authorization'] = `Bearer ${data.access_token}`;
        //console.log(data.token);
        await router.push('/');
      }catch(error){
        console.log(error);
        // Clear email input
document.querySelector('input[name="email"]').value = '';

// Clear password input
document.querySelector('input[name="password"]').value = '';
        await router.push('/login');

        }
      }
  

      
      return {
        submit
      }
    }
  }
  </script>


<style>
.form-signin {
  border: 1px solid #ccc;
  padding: 20px;
  max-width: 400px;
  margin: 0 auto;
  margin-top: 150px;
}

.blue-ocean {
  background-color: #007bff; /* Blue ocean color */
  border-color: #007bff; /* Blue ocean color */
  color: white; /* White font color */
  border-radius: 20px; /* Rounded corner */
  font-size: 18px; /* Increased font size */
}
</style>
