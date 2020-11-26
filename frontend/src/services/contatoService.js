import {ApiService} from './api.service'


const ContatosServices =  {
    listar: async() => {
        return ApiService.get('api/contatos/listar');
    },
    salvar: async(contato) => {
        contato.cep = contato.cep.replace("-", "");
        return ApiService.post('api/contatos/salvar/', contato);
    },
    atualizar: async(contato) => {
        //spring não precisa passar o id
        contato.cep = contato.cep.replace("-", "");
        return ApiService.put('api/contatos/alterar/', contato);
    },
    deletar: async (id) => {
        return ApiService.delete(`api/contatos/excluir/${id}`);
    }

}
export default ContatosServices;
