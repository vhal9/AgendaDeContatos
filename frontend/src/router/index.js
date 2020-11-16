import Vue from 'vue';
import Router from 'vue-router';

import Home from '../views/home';
import CadastrarContato from '../views/cadastrarContato';

Vue.use(Router);

const routes = [
    {
        name: Home,
        path: '/',
        component: Home,
    },
    {
        name: CadastrarContato,
        path:'/cadastrar',
        component: CadastrarContato
    }
]

const router = new Router ({routes})

export default router;