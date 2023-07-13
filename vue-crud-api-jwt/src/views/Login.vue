<template>
    <main class="form-signin">
      <form @submit.prevent="submit">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
  
        <div class="form-floating">
          <input type="email" class="form-control" name="email" placeholder="name@example.com">
          <label>Email</label>
        </div>
  
        <div class="form-floating">
          <input type="password" class="form-control" name="password" placeholder="Password">
          <label>Password</label>
        </div>
  
        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
      </form>
    </main>
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
        const form = new FormData(e.target);
  
        const inputs = Object.fromEntries(form.entries());
        console.log(inputs);
        const loginData = {
            email: inputs.email,
            password: inputs.password
        };
        const {data} = await axios.post('http://localhost:8086/api/v1/auth/authenticate', loginData);
        console.log(data);
        console.log(data['access_token']);


        localStorage.setItem('access_Token', data['access_token']);
        VueCookies.set('access_Token',  data['access_token'], '7d'); // Save the access token in a cookie for 7 days

        //this.setCookie('access_Token',  data['access_token'], 7);
       // axios.defaults.headers.common['Authorization'] = `Bearer ${data.access_token}`;
        //console.log(data.token);
        await router.push('/');
      }
  

      
      return {
        submit
      }
    }
  }
  </script>