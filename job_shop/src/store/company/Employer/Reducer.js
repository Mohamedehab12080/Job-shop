import {
  GET_EMPLOYER_FIELDS_FAILURE,
  GET_EMPLOYER_FIELDS_REQUEST,
  GET_EMPLOYER_FIELDS_SUCCESS,
  GET_EMPLOYER_POSTS_FAILURE,
  GET_EMPLOYER_POSTS_REQUEST,
  GET_EMPLOYER_POSTS_SUCCESS,
  GET_EMPLOYER_PROFILE_FAILURE,
  GET_EMPLOYER_PROFILE_REQUEST,
  GET_EMPLOYER_PROFILE_SUCCESS,
  UPDATE_EMPLOYER_CONTACTS_FAILURE,
  UPDATE_EMPLOYER_CONTACTS_SUCCESS,
  UPDATE_EMPLOYER_PROFILE_FAILURE,
  UPDATE_EMPLOYER_PROFILE_REQUEST,
  UPDATE_EMPLOYER_PROFILE_SUCCESS,
  UPDATE_POST_FAILURE,
  UPDATE_POST_REQUEST,
  UPDATE_POST_SUCCESS,
} from "./ActionType";

const initialState = {
  loading: false,
  data: null,
  error: null,
  posts: [],
  post: null,
  fields: [],
  field: null,
  response: null,
  employerData: null,
  isRquestUser: false,
  employerPosts: [],
};

export const employerReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_EMPLOYER_POSTS_FAILURE:
    case GET_EMPLOYER_FIELDS_FAILURE:
    case GET_EMPLOYER_PROFILE_FAILURE:
    case UPDATE_EMPLOYER_PROFILE_FAILURE:
    case UPDATE_EMPLOYER_CONTACTS_FAILURE:
      return { ...state, loading: false, error: action.payload };
    case GET_EMPLOYER_POSTS_REQUEST:
    case GET_EMPLOYER_FIELDS_REQUEST:
    case GET_EMPLOYER_PROFILE_REQUEST:
    case UPDATE_EMPLOYER_PROFILE_REQUEST:
    case UPDATE_EMPLOYER_PROFILE_REQUEST:
      return { ...state, loading: true, error: null };
    case GET_EMPLOYER_POSTS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        posts: action.payload,
        response: action.payload,
      };
    case GET_EMPLOYER_FIELDS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        fields: action.payload,
        response: action.payload,
      };
    case GET_EMPLOYER_PROFILE_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        response: action.payload,
        employerData: action.payload.employerDto,
        isRquestUser: action.payload.isRquestUser,
        employerPosts: action.payload.posts,
      };

    case UPDATE_EMPLOYER_CONTACTS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        employerData: {
          ...state.employerData,
          contacts: action.payload.contacts,
        },
        isRquestUser: state.isRquestUser,
      };
    case UPDATE_EMPLOYER_PROFILE_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        response: action.payload,
        employerData: action.payload,
        isRquestUser: state.isRquestUser,
      };
    default:
      return state;
  }
};
