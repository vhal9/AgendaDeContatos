import {ApiCepService} from './apiCep.service'


const CepServices =  {
    getEndereco: async(cep) => {
        return ApiCepService.get(`${cep}/json`);
    },
}

export default CepServices;