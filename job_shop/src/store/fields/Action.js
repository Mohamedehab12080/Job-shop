import { api } from "../../config/api"
import { FETCH_FIELDS_FAILURE, FETCH_FIELDS_SUCCESS } from "./ActionType";

export const fetchAllFields=()=>async(dispatch)=>
{
        try {
            const {data}=await api.get(`/api/fields/findAll`);
            dispatch({type:FETCH_FIELDS_SUCCESS,payload:data});
        } catch (error) {
            dispatch({type:FETCH_FIELDS_FAILURE,payload:error.message});
        }

}