import { createRouter, createWebHashHistory } from 'vue-router';
import Login from '../views/Login.vue';


const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '../views/HomeView.vue'),
    meta: {
      requiresAuth: true, // Add the 'requiresAuth' meta field to the route
    },
  },
  {
    path: '/Items',
    name: 'Items',
    component: () => import(/* webpackChunkName: "about" */ '../views/Items.vue'),
    meta: {
      requiresAuth: true, // Add the 'requiresAuth' meta field to the route
    },
  },

  {
    path: '/Monitor',
    name: 'Monitor',
    component: () => import(/* webpackChunkName: "about" */ '../views/Monitor.vue'),
    meta: {
      requiresAuth: true, // Add the 'requiresAuth' meta field to the route
    },
  },


  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  // Check if the route requires authentication
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    // Check if the bearer token is present
    //

    const sessionCookie = document.cookie.replace(
      /(?:(?:^|.*;\s*)access_Token\s*\=\s*([^;]*).*$)|^.*$/,
      '$1'
    );
    //console.log(sessionCookie);
    const token = sessionCookie; // Retrieve the token from local storage or any other storage mechanism

    if (token) {
      // Token exists, allow access to the route
      next();
    } else {
      // Token doesn't exist, redirect to the login page or display an error
      next('/login');
    }
  } else {
    // Route doesn't require authentication, allow access
    next();
  }
});

export default router;
