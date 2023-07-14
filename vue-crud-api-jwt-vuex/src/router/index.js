import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import Items from '../views/Items.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import Monitor from '../views/Monitor.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true, // Add the 'requiresAuth' meta field to the route
    },
  },
  {
    path: '/Items',
    name: 'Items',
    component: Items,
    meta: {
      requiresAuth: true, // Add the 'requiresAuth' meta field to the route
    },
  },

  {
    path: '/Monitor',
    name: 'Monitor',
    component: Monitor,
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
  },
  {
    path: '/register',
    name: 'register',
    component: Register,
  },
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
